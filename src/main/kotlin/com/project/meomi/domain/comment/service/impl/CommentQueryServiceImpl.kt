package com.project.meomi.domain.comment.service.impl

import com.project.meomi.domain.comment.domain.repository.CommentRepository
import com.project.meomi.domain.comment.presentation.data.dto.CommentDto
import com.project.meomi.domain.comment.presentation.data.dto.CommentListQueryDto
import com.project.meomi.domain.comment.service.CommentQueryService
import com.project.meomi.domain.comment.utils.CommentQueryConverter
import com.project.meomi.domain.user.utils.UserUtil
import com.project.meomi.global.annotation.TransactionWithReadOnly
import org.springframework.stereotype.Service

@Service
class CommentQueryServiceImpl(
    private val commentRepository: CommentRepository,
    private val commentQueryConverter: CommentQueryConverter,
    private val userUtil: UserUtil
): CommentQueryService {

    @TransactionWithReadOnly
    override fun findAllCommentByStudyId(dto: CommentDto): List<CommentListQueryDto> =
        commentRepository.findCommentByStudyId(dto.id)
            .map { commentQueryConverter.toQueryDto(it, isCommentMine(it.user.id)) }

    private fun isCommentMine(commentWriterId: Long) =
        userUtil.currentUser().id == commentWriterId

}