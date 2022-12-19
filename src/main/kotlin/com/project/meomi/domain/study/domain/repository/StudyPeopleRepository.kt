package com.project.meomi.domain.study.domain.repository

import com.project.meomi.domain.study.domain.StudyPeople
import org.springframework.data.repository.CrudRepository

interface StudyPeopleRepository: CrudRepository<StudyPeople, Long> {
    fun deleteStudyPeopleByStudyId(studyId: Long)
    fun findStudyPeopleByStudyId(studyId: Long): List<StudyPeople>
    fun existsConferencePeopleByStudyIdAndStudyUserId(studyId: Long, userId: Long): Boolean
}