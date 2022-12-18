package com.project.meomi.domain.conference.domain.repository

import com.project.meomi.domain.conference.domain.ConferencePeople
import org.springframework.data.repository.CrudRepository

interface ConferencePeopleRepository: CrudRepository<ConferencePeople, Long> {
    fun deleteConferencePeopleByUserId(id: Long)
    fun findConferencePeopleByConferenceId(id: Long): List<ConferencePeople>
    fun existsConferencePeopleByUserId(id: Long): Boolean
}