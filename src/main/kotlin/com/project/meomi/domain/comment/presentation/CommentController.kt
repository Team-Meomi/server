package com.project.meomi.domain.comment.presentation

import com.project.meomi.domain.comment.presentation.data.request.CreateCommentRequest
import com.project.meomi.domain.comment.presentation.data.request.UpdateCommentRequest
import com.project.meomi.domain.comment.presentation.data.response.CommentListResponse
import com.project.meomi.domain.comment.service.CommentQueryService
import com.project.meomi.domain.comment.service.CommentService
import com.project.meomi.domain.comment.utils.CommentConverter
import com.project.meomi.domain.comment.utils.CommentQueryConverter
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import javax.validation.Valid

@Controller
@RequestMapping("api/v1/user/comment/")
class CommentController(
    private val commentConverter: CommentConverter,
    private val commentQueryConverter: CommentQueryConverter,
    private val commentService: CommentService,
    private val commentQueryService: CommentQueryService
) {

    @GetMapping("{studyId}")
    fun findAllCommentByStudyId(@PathVariable studyId: Long): ResponseEntity<List<CommentListResponse>> =
        commentConverter.toDto(studyId)
            .let { commentQueryService.findAllCommentByStudyId(it) }
            .let { commentQueryConverter.toResponse(it) }
            .let { ResponseEntity.ok(it) }

    @PostMapping("{studyId}")
    fun createComment(@PathVariable studyId: Long, @RequestBody @Valid request: CreateCommentRequest): ResponseEntity<Void> =
        commentConverter.toDto(studyId, request)
            .let { commentService.createComment(it) }
            .let { ResponseEntity.status(HttpStatus.CREATED).build() }

    @PatchMapping("{commentId}")
    fun updateComment(@PathVariable commentId: Long, @RequestBody @Valid request: UpdateCommentRequest): ResponseEntity<Void> =
        commentConverter.toDto(commentId, request)
            .let { commentService.updateComment(it) }
            .let { ResponseEntity.ok().build() }


    @DeleteMapping("{commentId}")
    fun deleteComment(@PathVariable commentId: Long): ResponseEntity<Void> =
        commentConverter.toDto(commentId)
            .let { commentService.deleteComment(it) }
            .let { ResponseEntity.ok().build() }


}