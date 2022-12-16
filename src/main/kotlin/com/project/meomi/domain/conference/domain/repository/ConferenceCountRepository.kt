package com.project.meomi.domain.conference.domain.repository

import com.project.meomi.domain.conference.domain.ConferenceCount
import org.springframework.data.jpa.repository.Lock
import org.springframework.data.repository.CrudRepository
import javax.persistence.LockModeType

interface ConferenceCountRepository: CrudRepository<ConferenceCount, Long> {
    // 트랜젝션끼리의 충돌을 막기 위해 비관적락을 설정한다
    // LockModeType.PESSIMISTIC_WRITE: 다른 트랜젝션에서 읽기도 쓰지도 못한다
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    fun findConferenceCountByConferenceId(id: Long): ConferenceCount
}