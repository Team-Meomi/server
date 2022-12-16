package com.project.meomi.domain.user.exception

import com.project.meomi.global.error.exception.BasicException
import com.project.meomi.global.error.type.ErrorCode

class RefreshTokenExpiredException: BasicException(ErrorCode.REFRESH_TOKEN_EXPIRED)