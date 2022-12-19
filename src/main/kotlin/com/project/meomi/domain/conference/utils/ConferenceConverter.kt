package com.project.meomi.domain.conference.utils

import com.project.meomi.domain.conference.domain.Conference
import com.project.meomi.domain.conference.domain.ConferencePeople
import com.project.meomi.domain.conference.presentation.data.dto.ConferenceDto
import com.project.meomi.domain.conference.presentation.data.dto.ConferenceQueryDto
import com.project.meomi.domain.conference.presentation.data.request.ConferenceRentRequest
import com.project.meomi.domain.conference.presentation.data.request.CreateConferenceRequest
import com.project.meomi.domain.conference.presentation.data.request.UpdateConferenceRequest
import com.project.meomi.domain.conference.presentation.data.response.CheckConferenceResponse
import com.project.meomi.domain.conference.presentation.data.response.ConferenceResponse
import com.project.meomi.domain.user.domain.User

interface ConferenceConverter {

    fun toDto(request: CreateConferenceRequest): ConferenceDto
    fun toDto(request: UpdateConferenceRequest, id: Long): ConferenceDto
    fun toDto(request: ConferenceRentRequest): ConferenceDto
    fun toDto(id: Long): ConferenceDto
    fun toEntity(dto: ConferenceDto, user: User): Conference
    fun toEntity(conference: Conference, user: User): ConferencePeople
    fun toQueryDto(conference: Conference, isMine: Boolean, isStatus: Boolean, conferencePeople: List<ConferencePeople>): ConferenceQueryDto
    fun toResponse(dto: ConferenceQueryDto): ConferenceResponse
    fun toResponse(isStatus: Boolean): CheckConferenceResponse

}