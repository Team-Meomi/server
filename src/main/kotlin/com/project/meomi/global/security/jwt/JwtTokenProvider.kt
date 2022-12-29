package com.project.meomi.global.security.jwt

import com.project.meomi.domain.user.presentation.data.type.Role
import com.project.meomi.global.security.authentication.AuthDetailsService
import com.project.meomi.global.security.jwt.config.JwtProperties
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import org.springframework.stereotype.Component
import io.jsonwebtoken.security.Keys
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import java.security.Key
import java.nio.charset.StandardCharsets
import java.time.LocalDateTime
import java.util.Date
import java.util.Objects
import java.util.stream.Collector
import java.util.stream.Collectors

@Component
class JwtTokenProvider(
    private val jwtProperties: JwtProperties,
    private val authDetailsService: AuthDetailsService
) {

    private val accessTokenExpireTime = 1000 * 60 * 60 * 3L // 3시간
    private val refreshTokenExpireTime = 7 * 24 * 60 * 60 * 1000L // 1주

    private fun getSignInKey(secretKey: String): Key {
        val bytes = secretKey.toByteArray(StandardCharsets.UTF_8)
        return Keys.hmacShaKeyFor(bytes)
    }

    fun extractAllClaims(token: String): Claims =
        Jwts.parserBuilder()
            .setSigningKey(getSignInKey(jwtProperties.key))
            .build()
            .parseClaimsJws(token)
            .body

    fun getUserEmail(token: String): String =
        extractAllClaims(token).subject

    fun isTokenExpired(token: String): Boolean {
        runCatching {
            extractAllClaims(token).expiration
        }.onFailure {
            return true
        }
        return false
    }

    fun getExpiredAt(): LocalDateTime = LocalDateTime.now().plusSeconds(accessTokenExpireTime + 1000)

    private fun doGenerateToken(email: String, role: MutableList<Role>, expireTime: Long) =
        Jwts.builder()
            .setSubject(email)
            .claim("auth", role.stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList())
            )
            .setIssuedAt(Date(System.currentTimeMillis()))
            .setExpiration(Date(System.currentTimeMillis() + expireTime))
            .signWith(getSignInKey(jwtProperties.key))
            .compact()

    fun generateAccessToken(email: String, role: MutableList<Role>): String =
        doGenerateToken(email = email, role = role, expireTime = accessTokenExpireTime)

    fun generateRefreshToken(email: String, role: MutableList<Role>): String =
        doGenerateToken(email = email, role = role, expireTime = refreshTokenExpireTime)

    fun authentication(token: String): Authentication {
        val userDetail = authDetailsService.loadUserByUsername(getUserEmail(token))
        return UsernamePasswordAuthenticationToken(userDetail, "", userDetail.authorities)
    }

}