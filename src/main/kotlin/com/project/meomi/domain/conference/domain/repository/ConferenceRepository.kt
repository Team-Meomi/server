package com.project.meomi.domain.conference.domain.repository

import com.project.meomi.domain.conference.domain.Conference
import org.springframework.data.repository.CrudRepository

interface ConferenceRepository: CrudRepository<Conference, Long> {
    fun findConferenceById(id: Long): Conference?
}