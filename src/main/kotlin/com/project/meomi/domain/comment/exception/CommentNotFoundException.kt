package com.project.meomi.domain.comment.exception

import com.project.meomi.global.error.exception.BasicException
import com.project.meomi.global.error.type.ErrorCode

class CommentNotFoundException: BasicException(ErrorCode.COMMENT_NOT_FOUND)