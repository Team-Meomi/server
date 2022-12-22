package com.project.meomi.domain.study.domain

import com.project.meomi.domain.study.presentation.data.dto.StudyDto
import com.project.meomi.domain.user.domain.User
import com.project.meomi.global.entity.BaseTimeEntity
import org.hibernate.annotations.ColumnDefault
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction
import java.time.LocalDate
import javax.persistence.*

@Entity
class Study(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "study_id")
    val id: Long,
    var title: String,
    var content: String,
    var category: String,
    var date: LocalDate,
    val studyType: String,
    @ColumnDefault(value = "0")
    var count: Int,
    var maxCount: Int,
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "user_id")
    val user: User
): BaseTimeEntity() {
    fun updateStudy(dto: StudyDto, count: Int) {
        this.title = dto.title
        this.content = dto.content
        this.category = dto.category
        this.date = dto.date
        this.count = count
        this.maxCount = dto.maxCount
    }
    fun addCount() {
        this.count += 1
    }
    fun removeCount() {
        this.count -= 1
    }
}