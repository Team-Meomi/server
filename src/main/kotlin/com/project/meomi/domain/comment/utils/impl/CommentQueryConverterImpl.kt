package com.project.meomi.domain.comment.utils.impl

import com.project.meomi.domain.comment.domain.Comment
import com.project.meomi.domain.comment.presentation.data.dto.CommentListQueryDto
import com.project.meomi.domain.comment.presentation.data.response.CommentListResponse
import com.project.meomi.domain.comment.utils.CommentQueryConverter
import org.springframework.stereotype.Component

@Component
class CommentQueryConverterImpl: CommentQueryConverter {

    override fun toQueryDto(comment: Comment, isMine: Boolean): CommentListQueryDto =
        CommentListQueryDto(id = comment.id, comment = comment.comment, isMine = isMine, writer = CommentListQueryDto.UserDto(comment.user.id, comment.user.name, comment.user.stuNum))

    override fun toResponse(dto: List<CommentListQueryDto>): List<CommentListResponse> =
        dto.map { CommentListResponse(id = it.id, comment = it.comment, isMine = it.isMine, writer = it.writer) }

}