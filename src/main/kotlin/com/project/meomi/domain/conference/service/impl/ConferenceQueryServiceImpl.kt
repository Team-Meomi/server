package com.project.meomi.domain.conference.service.impl

import com.project.meomi.domain.conference.domain.repository.ConferencePeopleRepository
import com.project.meomi.domain.conference.domain.repository.ConferenceRepository
import com.project.meomi.domain.conference.exception.ConferenceNotFoundException
import com.project.meomi.domain.conference.presentation.data.dto.*
import com.project.meomi.domain.conference.service.ConferenceQueryService
import com.project.meomi.domain.conference.utils.ConferenceConverter
import com.project.meomi.domain.user.utils.UserUtil
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ConferenceQueryServiceImpl(
    private val conferenceRepository: ConferenceRepository,
    private val conferencePeopleRepository: ConferencePeopleRepository,
    private val conferenceConverter: ConferenceConverter,
    private val userUtil: UserUtil
) : ConferenceQueryService {

    @Transactional(readOnly = true, rollbackFor = [Exception::class])
    override fun checkConferenceIsRent(dto: ConferenceDto): Boolean =
        !conferenceRepository.existsConferenceByDateAndStartTimeAndEndTime(dto.date, dto.startTime, dto.endTime)

    @Transactional(readOnly = true, rollbackFor = [Exception::class])
    override fun findConferenceById(dto: ConferenceDto): ConferenceQueryDto {
        val conference = conferenceRepository.findConferenceById(dto.id)
            ?: throw ConferenceNotFoundException()

        val isStatus = conferencePeopleRepository.existsConferencePeopleByConferenceIdAndConferenceUserId(conference.id, userUtil.currentUser().id)
        val conferencePeople = conferencePeopleRepository.findConferencePeopleByConferenceId(conference.id)

        return conferenceConverter.toQueryDto(conference, isConferenceMine(conference.user.id), isStatus, conferencePeople)
    }

    private fun isConferenceMine(conferenceUserId: Long): Boolean =
        userUtil.currentUser().id == conferenceUserId

}