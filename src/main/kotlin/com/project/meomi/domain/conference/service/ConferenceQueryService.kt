package com.project.meomi.domain.conference.service

import com.project.meomi.domain.conference.presentation.data.dto.*

interface ConferenceQueryService {

    fun checkConferenceIsRent(dto: ConferenceDto): ConferenceRentDto
    fun findConferenceById(dto: ConferenceDto): ConferenceQueryDto
    fun findConferenceInfo(dto: ConferenceDto): ConferenceInfoDto
    fun findConferencePeople(dto: ConferenceDto): ConferencePeopleDto

}