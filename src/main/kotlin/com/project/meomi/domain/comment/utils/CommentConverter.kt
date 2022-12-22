package com.project.meomi.domain.comment.utils

import com.project.meomi.domain.comment.domain.Comment
import com.project.meomi.domain.comment.presentation.data.dto.CommentDto
import com.project.meomi.domain.comment.presentation.data.request.CreateCommentRequest
import com.project.meomi.domain.comment.presentation.data.request.UpdateCommentRequest
import com.project.meomi.domain.study.domain.Study
import com.project.meomi.domain.user.domain.User

interface CommentConverter {

    fun toDto(studyId: Long, request: CreateCommentRequest): CommentDto
    fun toDto(commentId: Long, request: UpdateCommentRequest): CommentDto
    fun toDto(id: Long): CommentDto
    fun toEntity(dto: CommentDto, study: Study, user: User): Comment

}