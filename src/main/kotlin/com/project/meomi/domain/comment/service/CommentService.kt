package com.project.meomi.domain.comment.service

import com.project.meomi.domain.comment.presentation.data.dto.CommentDto

interface CommentService {

    fun createComment(dto: CommentDto)
    fun updateComment(dto: CommentDto)
    fun deleteComment(dto: CommentDto)

}