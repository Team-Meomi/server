package com.project.meomi.domain.comment.service

import com.project.meomi.domain.comment.presentation.data.dto.CommentDto
import com.project.meomi.domain.comment.presentation.data.dto.CommentListQueryDto

interface CommentQueryService {

    fun findAllCommentByStudyId(dto: CommentDto): List<CommentListQueryDto>

}