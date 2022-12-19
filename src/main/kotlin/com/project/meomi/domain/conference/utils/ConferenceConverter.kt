package com.project.meomi.domain.conference.utils

import com.project.meomi.domain.conference.domain.Conference
import com.project.meomi.domain.conference.domain.ConferencePeople
import com.project.meomi.domain.conference.presentation.data.dto.*
import com.project.meomi.domain.conference.presentation.data.request.ConferenceRentRequest
import com.project.meomi.domain.conference.presentation.data.request.CreateConferenceRequest
import com.project.meomi.domain.conference.presentation.data.request.UpdateConferenceRequest
import com.project.meomi.domain.conference.presentation.data.response.ConferenceInfoResponse
import com.project.meomi.domain.conference.presentation.data.response.ConferencePeopleResponse
import com.project.meomi.domain.conference.presentation.data.response.ConferenceRentResponse
import com.project.meomi.domain.conference.presentation.data.response.ConferenceResponse
import com.project.meomi.domain.user.domain.User

interface ConferenceConverter {

    fun toDto(request: CreateConferenceRequest): ConferenceDto
    fun toDto(request: UpdateConferenceRequest, id: Long): ConferenceDto
    fun toDto(request: ConferenceRentRequest): ConferenceDto
    fun toDto(id: Long): ConferenceDto
    fun toEntity(dto: ConferenceDto, user: User): Conference
    fun toEntity(conference: Conference, user: User): ConferencePeople
    fun toQueryDto(conference: Conference, isMine: Boolean): ConferenceQueryDto
    fun toResponse(dto: ConferenceQueryDto): ConferenceResponse
    fun toResponse(dto: ConferenceInfoDto): ConferenceInfoResponse
    fun toResponse(dto: ConferencePeopleDto): ConferencePeopleResponse
    fun toResponse(dto: ConferenceRentDto): ConferenceRentResponse

}