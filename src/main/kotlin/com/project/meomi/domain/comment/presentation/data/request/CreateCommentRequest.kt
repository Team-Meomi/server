package com.project.meomi.domain.comment.presentation.data.request

import javax.validation.constraints.NotBlank

data class CreateCommentRequest(
    @field:NotBlank
    val comment: String
)