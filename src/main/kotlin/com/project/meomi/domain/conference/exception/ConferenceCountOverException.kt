package com.project.meomi.domain.conference.exception

import com.project.meomi.global.error.exception.BasicException
import com.project.meomi.global.error.type.ErrorCode

class ConferenceCountOverException: BasicException(ErrorCode.CONFERENCE_COUNT_OVER)