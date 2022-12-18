package com.project.meomi.domain.conference.service.impl

import com.project.meomi.domain.conference.domain.repository.ConferencePeopleRepository
import com.project.meomi.domain.conference.domain.repository.ConferenceRepository
import com.project.meomi.domain.conference.exception.ConferenceNotFoundException
import com.project.meomi.domain.conference.presentation.data.dto.ConferenceDto
import com.project.meomi.domain.conference.presentation.data.dto.ConferenceInfoDto
import com.project.meomi.domain.conference.presentation.data.dto.ConferencePeopleDto
import com.project.meomi.domain.conference.presentation.data.dto.ConferenceQueryDto
import com.project.meomi.domain.conference.service.ConferenceQueryService
import com.project.meomi.domain.conference.utils.ConferenceConverter
import com.project.meomi.domain.user.utils.UserUtil
import org.springframework.stereotype.Service

@Service
class ConferenceQueryServiceImpl(
    private val conferenceRepository: ConferenceRepository,
    private val conferencePeopleRepository: ConferencePeopleRepository,
    private val conferenceConverter: ConferenceConverter,
    private val userUtil: UserUtil
) : ConferenceQueryService {

    override fun findConferenceById(dto: ConferenceDto): ConferenceQueryDto =
        conferenceRepository.findConferenceById(dto.id)
            .let { it ?: throw ConferenceNotFoundException() }
            .let { conferenceConverter.toQueryDto(it, isConferenceMine(it.user.id)) }

    override fun findConferenceInfo(dto: ConferenceDto): ConferenceInfoDto {
        val conference = conferenceRepository.findConferenceById(dto.id)
            ?: throw ConferenceNotFoundException()
        val isStatus = conferencePeopleRepository.existsConferencePeopleByUserId(userUtil.currentUser().id)
        return ConferenceInfoDto(isStatus, conference.count)
    }

    override fun findConferencePeople(dto: ConferenceDto): ConferencePeopleDto {
        val conference = conferenceRepository.findConferenceById(dto.id)
            ?: throw ConferenceNotFoundException()
        val conferencePeople = conferencePeopleRepository.findConferencePeopleByConferenceId(conference.id)
        return ConferencePeopleDto(
            conferencePeople.map {
                ConferencePeopleDto.UserResponse(
                    it.user.id,
                    it.user.stuNum,
                    it.user.name,
                    it.user.gender
                )
            }
        )
    }

    private fun isConferenceMine(id: Long): Boolean =
        userUtil.currentUser().id == id

}