package com.project.meomi.domain.comment.presentation.data.request

import javax.validation.constraints.NotBlank

data class UpdateCommentRequest (
    @field:NotBlank
    val comment: String
)