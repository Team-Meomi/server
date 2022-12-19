package com.project.meomi.domain.conference.service

import com.project.meomi.domain.conference.presentation.data.dto.*

interface ConferenceQueryService {

    fun checkConferenceIsRent(dto: ConferenceDto): Boolean
    fun findConferenceById(dto: ConferenceDto): ConferenceQueryDto

}