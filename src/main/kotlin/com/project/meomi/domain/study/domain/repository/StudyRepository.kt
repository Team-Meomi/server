package com.project.meomi.domain.study.domain.repository

import com.project.meomi.domain.study.domain.Study
import org.springframework.data.jpa.repository.Lock
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import java.time.LocalDate
import javax.persistence.LockModeType

interface StudyRepository : CrudRepository<Study, Long> {

    // 트랜잭션의 충돌에 대비해 비관적 Lock을 건다.
    // 하나의 트랜잭션이 자원에 접근시 락을 걸어, 다른 트랜잭션이 접근하지 못하게 한다.
    @Lock(LockModeType.PESSIMISTIC_READ)
    fun findStudyById(studyId: Long): Study?
    fun existsByDateAndStudyType(date: LocalDate, studyType: String): Boolean
    fun findStudyByDateAndStudyType(date: LocalDate, studyType: String): List<Study>
    fun findAllByOrderByCreateAtDesc(): List<Study>
    fun findStudyByUserIdOrderByCreateAtDesc(userId: Long): List<Study>
    @Query("select study from Study study, StudyPeople studyPeople where studyPeople.user.id = :userId and studyPeople.study.id = study.id order by study.createAt desc")
    fun findJoinStudyByUserId(userId: Long): List<Study>
    fun findStudyByTitleContainsOrderByCreateAtDesc(title: String): List<Study>
    fun findStudyByCategoryOrderByCreateAtDesc(category: String?): List<Study>
    fun findStudyByTitleContainsAndCategoryOrderByCreateAtDesc(title: String, category: String): List<Study>
    fun findStudyByStudyTypeAndDate(studyType: String, date: LocalDate): Study

}