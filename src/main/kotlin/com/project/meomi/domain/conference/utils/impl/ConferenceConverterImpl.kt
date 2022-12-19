package com.project.meomi.domain.conference.utils.impl

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
import com.project.meomi.domain.conference.utils.ConferenceConverter
import com.project.meomi.domain.user.domain.User
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class ConferenceConverterImpl : ConferenceConverter {

    override fun toDto(request: CreateConferenceRequest): ConferenceDto =
        ConferenceDto(
            id = -1,
            title = request.title,
            content = request.content,
            category = request.category,
            date = request.date,
            startTime = request.startTime,
            endTime = request.endTime,
        )

    override fun toDto(request: UpdateConferenceRequest, id: Long): ConferenceDto =
        ConferenceDto(
            id = id,
            title = request.title,
            content = request.content,
            category = request.category,
            date = request.date,
            startTime = request.startTime,
            endTime = request.endTime,
        )

    override fun toDto(request: ConferenceRentRequest): ConferenceDto =
        ConferenceDto(
            id = -1,
            title = "",
            content = "",
            category = "",
            date = request.date,
            startTime = request.startTime,
            endTime = request.endTime,
        )

    override fun toDto(id: Long): ConferenceDto =
        ConferenceDto(
            id = id,
            title = "",
            content = "",
            category = "",
            date = LocalDate.now(),
            startTime = -1,
            endTime = -1,
        )

    override fun toEntity(dto: ConferenceDto, user: User): Conference =
        Conference(
            id = 0,
            title = dto.title,
            content = dto.content,
            category = dto.category,
            date = dto.date,
            startTime = dto.startTime,
            endTime = dto.endTime,
            user = user,
            count = 0
        )

    override fun toEntity(conference: Conference, user: User): ConferencePeople =
        ConferencePeople(id = -1, conference = conference, user = user)

    override fun toQueryDto(conference: Conference, isMine: Boolean): ConferenceQueryDto =
        ConferenceQueryDto(
            id = conference.id,
            title = conference.title,
            content = conference.content,
            category = conference.category,
            date = conference.date,
            startTime = conference.startTime,
            endTime = conference.endTime,
            isMine = isMine,
            user = conference.user
        )

    override fun toResponse(dto: ConferenceQueryDto): ConferenceResponse =
        ConferenceResponse(
            id = dto.id,
            title = dto.title,
            content = dto.content,
            category = dto.category,
            date = dto.date,
            startTime = dto.startTime,
            endTime = dto.endTime,
            isMine = dto.isMine,
            user = ConferenceResponse.UserResponse(
                dto.user.id, dto.user.name, dto.user.gender
            )
        )

    override fun toResponse(dto: ConferenceInfoDto): ConferenceInfoResponse =
        ConferenceInfoResponse(dto.isStatus, dto.count)

    override fun toResponse(dto: ConferencePeopleDto): ConferencePeopleResponse =
        ConferencePeopleResponse(dto.list)

    override fun toResponse(dto: ConferenceRentDto): ConferenceRentResponse =
        ConferenceRentResponse(dto.isStatus)

}