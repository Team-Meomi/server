package com.project.meomi.domain.conference.service.impl

import com.project.meomi.domain.conference.domain.Conference
import com.project.meomi.domain.conference.domain.repository.ConferencePeopleRepository
import com.project.meomi.domain.conference.domain.repository.ConferenceRepository
import com.project.meomi.domain.conference.exception.ConferenceCountOverException
import com.project.meomi.domain.conference.exception.ConferenceNotFoundException
import com.project.meomi.domain.conference.exception.DuplicateConferenceApplicantException
import com.project.meomi.domain.conference.presentation.data.dto.ConferenceDto
import com.project.meomi.domain.conference.service.ConferenceService
import com.project.meomi.domain.conference.utils.ConferenceConverter
import com.project.meomi.domain.user.utils.UserUtil
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ConferenceServiceImpl(
    private val conferenceRepository: ConferenceRepository,
    private val conferencePeopleRepository: ConferencePeopleRepository,
    private val conferenceConverter: ConferenceConverter,
    private val userUtil: UserUtil
): ConferenceService {

    @Transactional(rollbackFor = [Exception::class])
    override fun createConference(dto: ConferenceDto) {
        conferenceConverter.toEntity(dto, userUtil.currentUser())
            .let { conferenceRepository.save(it) }
    }

    @Transactional(rollbackFor = [Exception::class])
    override fun joinConference(dto: ConferenceDto) {
        val conference = conferenceRepository.findConferenceById(dto.id)
            ?: throw ConferenceNotFoundException()

        isSmallerThanForty(conference.count)
        if(conferencePeopleRepository.existsConferencePeopleByConferenceIdAndConferenceUserId(conference.id, userUtil.currentUser().id)) {
            throw DuplicateConferenceApplicantException()
        }

        conferenceConverter.toEntity(conference, userUtil.currentUser())
            .let { conferencePeopleRepository.save(it) }

        conference.addCount()
    }

    @Transactional(rollbackFor = [Exception::class])
    override fun cancelConference(dto: ConferenceDto) {
        val conference = conferenceRepository.findConferenceById(dto.id)
            ?: throw ConferenceNotFoundException()

        conference.removeCount()
        conferencePeopleRepository.deleteConferencePeopleByUserId(userUtil.currentUser().id)
    }

    @Transactional(rollbackFor = [Exception::class])
    override fun updateConference(dto: ConferenceDto) {
        conferenceRepository.findConferenceById(dto.id)
            .let { it ?: throw ConferenceNotFoundException() }
            .let { it.updateConference(dto, it.count) }
    }

    @Transactional(rollbackFor = [Exception::class])
    override fun deleteConference(dto: ConferenceDto) {
        conferenceRepository.findConferenceById(dto.id)
            .let { it ?: throw ConferenceNotFoundException() }
            .let { conferenceRepository.delete(it) }
    }

    private fun isSmallerThanForty(count: Int): Boolean {
        if (count >= 40) throw ConferenceCountOverException()
        return true
    }

}