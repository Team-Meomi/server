package com.project.meomi.domain.comment.utils

import com.project.meomi.domain.comment.domain.Comment
import com.project.meomi.domain.comment.presentation.data.dto.CommentListQueryDto
import com.project.meomi.domain.comment.presentation.data.response.CommentListResponse

interface CommentQueryConverter {

    fun toQueryDto(comment: Comment, isMine: Boolean): CommentListQueryDto
    fun toResponse(dto: List<CommentListQueryDto>): List<CommentListResponse>

}