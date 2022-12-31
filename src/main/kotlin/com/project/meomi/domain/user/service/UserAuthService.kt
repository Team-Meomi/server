package com.project.meomi.domain.user.service

import com.project.meomi.domain.user.presentation.data.dto.ReissueTokenDto
import com.project.meomi.domain.user.presentation.data.dto.TokenDto
import com.project.meomi.domain.user.presentation.data.dto.UserDto

interface UserAuthService {

    fun signUp(dto: UserDto)

    fun signIn(dto: UserDto): TokenDto

    fun reissueToken(dto: ReissueTokenDto): TokenDto

    fun logout()

}