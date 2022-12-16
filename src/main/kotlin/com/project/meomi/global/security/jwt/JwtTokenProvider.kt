package com.project.meomi.global.security.jwt

import com.project.meomi.global.security.authentication.AuthDetailsService
import com.project.meomi.global.security.jwt.config.JwtProperties
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import org.springframework.stereotype.Component
import io.jsonwebtoken.security.Keys
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import java.security.Key
import java.nio.charset.StandardCharsets
import java.time.LocalDateTime
import java.util.Date

@Component
class JwtTokenProvider(
    private val jwtProperties: JwtProperties,
    private val authDetailsService: AuthDetailsService
) {

    private val accessTokenExpireTime = 1000 * 60 * 60 * 3L // 3시간
    private val refreshTokenExpireTime = 7 * 24 * 60 * 60 * 1000L // 1주

    enum class TokenType(val value: String) {
        ACCESS_TOKEN("accessToken"),
        REFRESH_TOKEN("refreshToken")
    }

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

    private fun doGenerateToken(email: String, tokenType: TokenType, expireTime: Long) =
        Jwts.builder()
            .setSubject(email)
            .claim("tokenType", tokenType.value)
            .setIssuedAt(Date(System.currentTimeMillis()))
            .setExpiration(Date(System.currentTimeMillis() + expireTime))
            .signWith(getSignInKey(jwtProperties.key))
            .compact()

    fun generateAccessToken(email: String): String =
        doGenerateToken(email = email, tokenType = TokenType.ACCESS_TOKEN, expireTime = accessTokenExpireTime)

    fun generateRefreshToken(email: String): String =
        doGenerateToken(email = email, tokenType = TokenType.REFRESH_TOKEN, expireTime = refreshTokenExpireTime)

    fun authentication(token: String): Authentication =
        getUserEmail(token)
            .let { authDetailsService.loadUserByUsername(it) }
            .let { UsernamePasswordAuthenticationToken(it, "", it.authorities) }

}