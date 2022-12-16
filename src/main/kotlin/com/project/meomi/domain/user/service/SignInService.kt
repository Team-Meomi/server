package com.project.meomi.domain.user.service

import com.project.meomi.domain.user.presentation.data.dto.TokenDto
import com.project.meomi.domain.user.presentation.data.dto.UserDto

interface SignInService {

    fun signIn(dto: UserDto): TokenDto

}