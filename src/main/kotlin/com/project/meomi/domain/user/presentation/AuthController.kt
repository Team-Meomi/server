package com.project.meomi.domain.user.presentation

import com.project.meomi.domain.user.presentation.data.request.SignInRequest
import com.project.meomi.domain.user.presentation.data.request.SignUpRequest
import com.project.meomi.domain.user.presentation.data.response.TokenResponse
import com.project.meomi.domain.user.service.ReissueTokenService
import com.project.meomi.domain.user.service.SignInService
import com.project.meomi.domain.user.service.SignUpService
import com.project.meomi.domain.user.utils.UserConverter
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@Controller
@RequestMapping("api/v1/auth")
class AuthController(
    private val userConverter: UserConverter,
    private val signUpService: SignUpService,
    private val signInService: SignInService,
    private val reissueTokenService: ReissueTokenService
) {

    @PostMapping("/signup")
    fun signup(@RequestBody @Valid request: SignUpRequest): ResponseEntity<Void> {
        userConverter.toDto(request)
            .let { signUpService.signUp(it) }
            .let { return ResponseEntity.status(HttpStatus.CREATED).build() }
    }

    @PostMapping("/signin")
    fun signin(@RequestBody @Valid request: SignInRequest): ResponseEntity<TokenResponse> =
        userConverter.toDto(request)
            .let { signInService.signIn(it) }
            .let { userConverter.toResponse(it) }
            .let { ResponseEntity.ok(it) }

    @PatchMapping
    fun reissue(@RequestHeader("Refresh-Token") refreshToken: String): ResponseEntity<TokenResponse> =
        userConverter.toDto(refreshToken)
            .let { reissueTokenService.reissueToken(it) }
            .let { userConverter.toResponse(it) }
            .let { ResponseEntity.ok(it) }

}