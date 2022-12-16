package com.project.meomi.domain.conference.utils.impl

import com.project.meomi.domain.conference.domain.Conference
import com.project.meomi.domain.conference.domain.ConferencePeople
import com.project.meomi.domain.conference.presentation.data.dto.ConferenceDto
import com.project.meomi.domain.conference.presentation.data.request.CreateConferenceRequest
import com.project.meomi.domain.conference.utils.ConferenceConverter
import com.project.meomi.domain.user.domain.User
import org.springframework.stereotype.Service

@Service
class ConferenceConverterImpl: ConferenceConverter {

    override fun toDto(request: CreateConferenceRequest): ConferenceDto =
        ConferenceDto(id = -1, title = request.title, content = request.content, date = request.date, startTime = request.startTime, endTime = request.endTime)

    override fun toEntity(dto: ConferenceDto, user: User): Conference =
        Conference(id = -1, title = dto.title, content = dto.content, date = dto.date, startTime = dto.startTime, endTime = dto.endTime, user = user)

    override fun toEntity(conference: Conference, user: User): ConferencePeople =
        ConferencePeople(id = -1, conference = conference, user = user)

}