package com.project.meomi.domain.conference.domain.repository

import com.project.meomi.domain.conference.domain.Conference
import org.springframework.data.repository.CrudRepository
import java.time.LocalDate

interface ConferenceRepository: CrudRepository<Conference, Long> {
//    @Lock(LockModeType.PESSIMISTIC_WRITE)
    fun findConferenceById(id: Long): Conference?
    fun existsConferenceByDateAndStartTimeAndEndTime(date: LocalDate, startTime: Int, endTime: Int): Boolean
}