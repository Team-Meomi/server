package com.project.meomi.domain.comment.utils.impl

import com.project.meomi.domain.comment.domain.Comment
import com.project.meomi.domain.comment.presentation.data.dto.CommentDto
import com.project.meomi.domain.comment.presentation.data.request.CreateCommentRequest
import com.project.meomi.domain.comment.presentation.data.request.UpdateCommentRequest
import com.project.meomi.domain.comment.utils.CommentConverter
import com.project.meomi.domain.study.domain.Study
import com.project.meomi.domain.user.domain.User
import org.springframework.stereotype.Component

@Component
class CommentConverterImpl: CommentConverter {

    override fun toDto(studyId: Long, request: CreateCommentRequest): CommentDto =
        CommentDto(id = studyId, comment = request.comment)

    override fun toDto(commentId: Long, request: UpdateCommentRequest): CommentDto =
        CommentDto(id = commentId, comment = request.comment)

    override fun toDto(id: Long): CommentDto =
        CommentDto(id = id, comment = "")

    override fun toEntity(dto: CommentDto, study: Study, user: User): Comment =
        Comment(id = -1, comment = dto.comment, study = study, user = user)

}