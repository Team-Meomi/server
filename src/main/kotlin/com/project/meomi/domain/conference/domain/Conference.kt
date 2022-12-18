package com.project.meomi.domain.conference.domain

import com.project.meomi.domain.conference.presentation.data.dto.ConferenceDto
import com.project.meomi.domain.user.domain.User
import org.hibernate.annotations.ColumnDefault
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction
import java.time.LocalDate
import javax.persistence.*

@Entity
class Conference(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "conference_id")
    val id: Long,
    var title: String,
    var content: String,
    var category: String,
    var date: LocalDate,
    var startTime: Int,
    var endTime: Int,
    @ColumnDefault(value = "0")
    var count: Int,
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "user_id")
    val user: User
) {
    fun updateConference(dto: ConferenceDto, count: Int) {
        this.title = dto.title
        this.content = dto.content
        this.date = dto.date
        this.startTime = dto.startTime
        this.endTime = dto.endTime
        this.count = count
    }
    fun addCount() {
        this.count += 1
    }
    fun removeCount() {
        this.count -= 1
    }
}