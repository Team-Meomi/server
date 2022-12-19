package com.project.meomi.domain.study.exception

import com.project.meomi.global.error.exception.BasicException
import com.project.meomi.global.error.type.ErrorCode

class StudyCountOverException: BasicException(ErrorCode.STUDY_COUNT_OVER)