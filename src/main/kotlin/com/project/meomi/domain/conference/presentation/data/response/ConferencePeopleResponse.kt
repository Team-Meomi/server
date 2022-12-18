package com.project.meomi.domain.conference.presentation.data.response

import com.project.meomi.domain.conference.presentation.data.dto.ConferencePeopleDto

data class ConferencePeopleResponse(
    val list: List<ConferencePeopleDto.UserResponse>
)