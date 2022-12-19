package com.project.meomi.domain.conference.domain.repository

import com.project.meomi.domain.conference.domain.ConferencePeople
import org.springframework.data.repository.CrudRepository

interface ConferencePeopleRepository: CrudRepository<ConferencePeople, Long> {
    fun deleteConferencePeopleByUserId(conferenceId: Long)
    fun findConferencePeopleByConferenceId(conferenceId: Long): List<ConferencePeople>
    fun existsConferencePeopleByConferenceIdAndConferenceUserId(conferenceId: Long, userId: Long): Boolean
}