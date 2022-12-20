package com.project.meomi.domain.study.domain.repository

import com.project.meomi.domain.study.domain.Study
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import java.time.LocalDate

interface StudyRepository : CrudRepository<Study, Long> {

    fun findStudyById(studyId: Long): Study?
    fun existsByDateAndStudyType(date: LocalDate, studyType: String): Boolean
    fun findAllByOrderByCreateAtDesc(): List<Study>
    fun findStudyByUserIdOrderByCreateAtDesc(userId: Long): List<Study>
    @Query("select study from Study study, StudyPeople studyPeople where studyPeople.user.id = :userId and studyPeople.id = study.id order by study.createAt desc ")
    fun findJoinStudyByUserId(userId: Long): List<Study>

}