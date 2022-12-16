package com.project.meomi.domain.user.service

import com.project.meomi.domain.user.presentation.data.dto.UserDto

interface SignUpService {

    fun signUp(dto: UserDto)

}