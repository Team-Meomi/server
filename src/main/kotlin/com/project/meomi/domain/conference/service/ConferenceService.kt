package com.project.meomi.domain.conference.service

import com.project.meomi.domain.conference.presentation.data.dto.ConferenceDto

interface ConferenceService {

    fun createConference(dto: ConferenceDto)
    fun joinConference(dto: ConferenceDto)
    fun cancelConference(dto: ConferenceDto)
    fun updateConference(dto: ConferenceDto)
    fun deleteConference(dto: ConferenceDto)

}