package com.project.meomi.global.error.exception

import com.project.meomi.global.error.type.ErrorCode

open class BasicException(
    val errorCode: ErrorCode
): RuntimeException(errorCode.message)