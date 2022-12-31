package com.project.meomi.domain.user.presentation

import com.project.meomi.domain.user.presentation.data.request.SignInRequest
import com.project.meomi.domain.user.presentation.data.request.SignUpRequest
import com.project.meomi.domain.user.presentation.data.response.TokenResponse
import com.project.meomi.domain.user.service.UserAuthService
import com.project.meomi.domain.user.utils.UserConverter
import com.project.meomi.domain.user.utils.UserQueryConverter
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("api/v1/auth")
class AuthController(
    private val userConverter: UserConverter,
    private val userQueryConverter: UserQueryConverter,
    private val userAuthService: UserAuthService
) {

    @PostMapping("/signup")
    fun signup(@RequestBody @Valid request: SignUpRequest): ResponseEntity<Void> =
        userConverter.toDto(request)
            .let { userAuthService.signUp(it) }
            .let { ResponseEntity.status(HttpStatus.CREATED).build() }

    @PostMapping("/signin")
    fun signin(@RequestBody @Valid request: SignInRequest): ResponseEntity<TokenResponse> =
        userConverter.toDto(request)
            .let { userAuthService.signIn(it) }
            .let { userQueryConverter.toResponse(it) }
            .let { ResponseEntity.ok(it) }

    @PatchMapping
    fun reissue(@RequestHeader("RefreshToken") refreshToken: String): ResponseEntity<TokenResponse> =
        userConverter.toDto(refreshToken)
            .let { userAuthService.reissueToken(it) }
            .let { userQueryConverter.toResponse(it) }
            .let { ResponseEntity.ok(it) }

    @DeleteMapping
    fun logout(): ResponseEntity<Void> =
        userAuthService.logout()
            .let { ResponseEntity.status(HttpStatus.OK).build() }

}