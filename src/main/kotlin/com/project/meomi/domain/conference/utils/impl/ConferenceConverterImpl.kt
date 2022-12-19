package com.project.meomi.domain.conference.utils.impl

import com.project.meomi.domain.conference.domain.Conference
import com.project.meomi.domain.conference.domain.ConferencePeople
import com.project.meomi.domain.conference.presentation.data.dto.ConferenceDto
import com.project.meomi.domain.conference.presentation.data.dto.ConferenceQueryDto
import com.project.meomi.domain.conference.presentation.data.request.ConferenceRentRequest
import com.project.meomi.domain.conference.presentation.data.request.CreateConferenceRequest
import com.project.meomi.domain.conference.presentation.data.request.UpdateConferenceRequest
import com.project.meomi.domain.conference.presentation.data.response.CheckConferenceResponse
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
            maxCount = request.maxCount
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
            maxCount = request.maxCount
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
            maxCount = -1
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
            maxCount = -1
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
            count = 0,
            maxCount = dto.maxCount
        )

    override fun toEntity(conference: Conference, user: User): ConferencePeople =
        ConferencePeople(id = -1, conference = conference, user = user)

    override fun toQueryDto(
        conference: Conference,
        isMine: Boolean,
        isStatus: Boolean,
        conferencePeople: List<ConferencePeople>
    ): ConferenceQueryDto =
        ConferenceQueryDto(
            id = conference.id,
            title = conference.title,
            content = conference.content,
            category = conference.category,
            date = conference.date,
            startTime = conference.startTime,
            endTime = conference.endTime,
            isMine = isMine,
            isStatus = isStatus,
            writer = ConferenceQueryDto.UserResponse(
                conference.user.id,
                conference.user.stuNum,
                conference.user.name,
                conference.user.gender
            ),
            count = ConferenceQueryDto.CountResponse(conference.count, conference.maxCount),
            list = conferencePeople.map {
                ConferenceQueryDto.UserResponse(
                    it.user.id,
                    it.user.stuNum,
                    it.user.name,
                    it.user.gender
                )
            }
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
            isStatus = dto.isStatus,
            writer = dto.writer,
            count = dto.count,
            list = dto.list
        )

    override fun toResponse(isStatus: Boolean): CheckConferenceResponse =
        CheckConferenceResponse(isStatus)

}