package com.project.meomi.domain.study.domain.repository

import com.project.meomi.domain.study.domain.StudyPeople
import org.springframework.data.repository.CrudRepository
import java.time.LocalDate

interface StudyPeopleRepository: CrudRepository<StudyPeople, Long>, StudyPeopleRepositoryCustom {

    fun deleteStudyPeopleByStudyIdAndUserId(studyId: Long, userId: Long)
    fun findStudyPeopleByStudyId(studyId: Long): List<StudyPeople>
    fun existsByStudyIdAndUserId(studyId: Long, userId: Long): Boolean
    fun existsStudyPeopleByUserIdAndStudyDate(userId: Long, date: LocalDate): Boolean

}