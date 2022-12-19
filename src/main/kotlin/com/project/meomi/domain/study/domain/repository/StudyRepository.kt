package com.project.meomi.domain.study.domain.repository

import com.project.meomi.domain.study.domain.Study
import org.springframework.data.repository.CrudRepository
import java.time.LocalDate

interface StudyRepository: CrudRepository<Study, Long> {
    fun findStudyById(id: Long): Study?
    fun existsStudyByDate(date: LocalDate): Boolean
}