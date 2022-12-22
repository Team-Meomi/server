package com.project.meomi.domain.comment.service.impl

import com.project.meomi.domain.comment.domain.repository.CommentRepository
import com.project.meomi.domain.comment.exception.CommentNotFoundException
import com.project.meomi.domain.comment.presentation.data.dto.CommentDto
import com.project.meomi.domain.comment.service.CommentService
import com.project.meomi.domain.comment.utils.CommentConverter
import com.project.meomi.domain.study.domain.repository.StudyRepository
import com.project.meomi.domain.study.exception.StudyNotFountException
import com.project.meomi.domain.user.utils.UserUtil
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CommentServiceImpl(
    private val commentRepository: CommentRepository,
    private val studyRepository: StudyRepository,
    private val commentConverter: CommentConverter,
    private val userUtil: UserUtil
): CommentService {

    @Transactional(rollbackFor = [Exception::class])
    override fun createComment(dto: CommentDto) {
        val study = studyRepository.findStudyById(dto.id) ?: throw StudyNotFountException()
        val user = userUtil.currentUser()
        commentConverter.toEntity(dto, study, user)
            .let { commentRepository.save(it) }
    }

    @Transactional(rollbackFor = [Exception::class])
    override fun updateComment(dto: CommentDto) {
        commentRepository.findCommentById(dto.id)
            .let {
                it ?: throw CommentNotFoundException()
                it.updateComment(dto.comment)
            }

    }

    @Transactional(rollbackFor = [Exception::class])
    override fun deleteComment(dto: CommentDto) {
        commentRepository.deleteById(dto.id)
    }

}