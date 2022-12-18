package com.project.meomi.domain.conference.service

import com.project.meomi.domain.conference.presentation.data.dto.ConferenceDto
import com.project.meomi.domain.conference.presentation.data.dto.ConferenceInfoDto
import com.project.meomi.domain.conference.presentation.data.dto.ConferencePeopleDto
import com.project.meomi.domain.conference.presentation.data.dto.ConferenceQueryDto

interface ConferenceQueryService {

    fun findConferenceById(dto: ConferenceDto): ConferenceQueryDto
    fun findConferenceInfo(dto: ConferenceDto): ConferenceInfoDto
    fun findConferencePeople(dto: ConferenceDto): ConferencePeopleDto

}