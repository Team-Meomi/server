package com.project.meomi.domain.user.exception

import com.project.meomi.global.error.exception.BasicException
import com.project.meomi.global.error.type.ErrorCode

class PasswordNotCorrectException: BasicException(ErrorCode.PASSWORD_NOT_CORRECT)