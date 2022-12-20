package com.project.meomi.domain.user.presentation

import com.project.meomi.domain.user.presentation.data.request.SignInRequest
import com.project.meomi.domain.user.presentation.data.request.SignUpRequest
import com.project.meomi.domain.user.presentation.data.response.TokenResponse
import com.project.meomi.domain.user.service.ReissueTokenService
import com.project.meomi.domain.user.service.SignInService
import com.project.meomi.domain.user.service.SignUpService
import com.project.meomi.domain.user.utils.UserConverter
import com.project.meomi.domain.user.utils.UserQueryConverter
import io.swagger.annotations.ApiOperation
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("api/v1/user/auth")
class AuthController(
    private val userConverter: UserConverter,
    private val userQueryConverter: UserQueryConverter,
    private val signUpService: SignUpService,
    private val signInService: SignInService,
    private val reissueTokenService: ReissueTokenService,
) {

    @ApiOperation(value = "회원가입", notes = "사용자 초기 정보를 통해 회원가입을 진행합니다.")
    @PostMapping("/signup")
    fun signup(@RequestBody @Valid request: SignUpRequest): ResponseEntity<Void> =
        userConverter.toDto(request)
            .let { signUpService.signUp(it) }
            .let { ResponseEntity.status(HttpStatus.CREATED).build() }

    @ApiOperation(value = "로그인", notes = "인증 정보(이메일, 패스워드)를 통해 로그인을 진행 후 성공시 토큰을 발급합니다.")
    @PostMapping("/signin")
    fun signin(@RequestBody @Valid request: SignInRequest): ResponseEntity<TokenResponse> =
        userConverter.toDto(request)
            .let { signInService.signIn(it) }
            .let { userQueryConverter.toResponse(it) }
            .let { ResponseEntity.ok(it) }

    @ApiOperation(value = "토큰 재발급", notes = "refreshToken을 통해 토큰을 재발급합니다.")
    @PatchMapping
    fun reissue(@RequestHeader("Refresh-Token") refreshToken: String): ResponseEntity<TokenResponse> =
        userConverter.toDto(refreshToken)
            .let { reissueTokenService.reissueToken(it) }
            .let { userQueryConverter.toResponse(it) }
            .let { ResponseEntity.ok(it) }

}