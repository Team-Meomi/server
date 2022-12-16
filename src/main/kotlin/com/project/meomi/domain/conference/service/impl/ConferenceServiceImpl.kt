package com.project.meomi.domain.conference.service.impl

import com.project.meomi.domain.conference.domain.Conference
import com.project.meomi.domain.conference.domain.ConferenceCount
import com.project.meomi.domain.conference.domain.repository.ConferenceCountRepository
import com.project.meomi.domain.conference.domain.repository.ConferencePeopleRepository
import com.project.meomi.domain.conference.domain.repository.ConferenceRepository
import com.project.meomi.domain.conference.exception.ConferenceCountOverException
import com.project.meomi.domain.conference.exception.ConferenceNotFoundException
import com.project.meomi.domain.conference.presentation.data.dto.ConferenceDto
import com.project.meomi.domain.conference.service.ConferenceService
import com.project.meomi.domain.conference.utils.ConferenceConverter
import com.project.meomi.domain.user.utils.UserUtil
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ConferenceServiceImpl(
    private val conferenceRepository: ConferenceRepository,
    private val conferenceCountRepository: ConferenceCountRepository,
    private val conferencePeopleRepository: ConferencePeopleRepository,
    private val conferenceConverter: ConferenceConverter,
    private val userUtil: UserUtil
): ConferenceService {

    @Transactional(rollbackFor = [Exception::class])
    override fun createConference(dto: ConferenceDto) {
        conferenceConverter.toEntity(dto, userUtil.currentUser())
            .let { conferenceRepository.save(it) }
    }

    @Transactional(readOnly = true, rollbackFor = [Exception::class])
    override fun joinConference(dto: ConferenceDto) {
        val conference = conferenceRepository.findConferenceById(dto.id)
            ?: throw ConferenceNotFoundException()
        val conferenceCount = findConferenceCount(dto.id)
        isSmallerThanForty(conferenceCount.count)
        conferenceCount.addCount()
        conferenceConverter.toEntity(conference, userUtil.currentUser())
            .let { conferencePeopleRepository.save(it) }
    }

    @Transactional(readOnly = true, rollbackFor = [Exception::class])
    override fun cancelConference(dto: ConferenceDto) {
        val conferenceCount = findConferenceCount(dto.id)
        conferenceCount.removeCount()
        conferencePeopleRepository.deleteConferencePeopleByUserId(userUtil.currentUser().id)
    }

    override fun updateConference(dto: ConferenceDto) {
        validateConference(dto.id).updateConference(dto)
    }

    override fun deleteConference(dto: ConferenceDto) {
        validateConference(dto.id)
            .let { conferenceRepository.deleteById(it.id) }
    }

    // JPA 비관적 Lock이 적용된 카운트를 조회하는 메서드
    private fun findConferenceCount(id: Long): ConferenceCount =
        conferenceCountRepository.findConferenceCountByConferenceId(id)

    private fun isSmallerThanForty(count: Long): Boolean {
        if (count >= 40) throw ConferenceCountOverException()
        return true
    }

    private fun validateConference(id: Long): Conference =
        conferenceRepository.findConferenceById(id) ?: throw ConferenceNotFoundException()

}