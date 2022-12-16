package com.project.meomi.domain.user.utils

import com.project.meomi.domain.user.presentation.data.dto.UserDto
import com.project.meomi.domain.user.presentation.data.type.ValidatorType

interface UserValidator {

    fun validate(validatorType: ValidatorType, dto: UserDto)

}