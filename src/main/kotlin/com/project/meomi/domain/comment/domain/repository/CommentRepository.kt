package com.project.meomi.domain.comment.domain.repository

import com.project.meomi.domain.comment.domain.Comment
import org.springframework.data.repository.CrudRepository

interface CommentRepository: CrudRepository<Comment, Long> {

    fun findCommentById(commentId: Long): Comment?
    fun findCommentByStudyId(studyId: Long): List<Comment>

}