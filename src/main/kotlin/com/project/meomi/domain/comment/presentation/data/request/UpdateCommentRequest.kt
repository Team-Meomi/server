package com.project.meomi.domain.comment.presentation.data.request

import javax.validation.constraints.NotNull

data class UpdateCommentRequest (
    @field:NotNull
    val comment: String
)