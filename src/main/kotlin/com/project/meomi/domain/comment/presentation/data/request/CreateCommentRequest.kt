package com.project.meomi.domain.comment.presentation.data.request

import javax.validation.constraints.NotNull

data class CreateCommentRequest(
    @field:NotNull
    val comment: String
)