package com.project.meomi.domain.conference.utils

import com.project.meomi.domain.conference.domain.Conference
import com.project.meomi.domain.conference.domain.ConferencePeople
import com.project.meomi.domain.conference.presentation.data.dto.ConferenceDto
import com.project.meomi.domain.conference.presentation.data.request.CreateConferenceRequest
import com.project.meomi.domain.user.domain.User

interface ConferenceConverter {

    fun toDto(request: CreateConferenceRequest): ConferenceDto
    fun toEntity(dto: ConferenceDto, user: User): Conference
    fun toEntity(conference: Conference, user: User): ConferencePeople

}