package com.project.meomi.domain.study.domain

import com.project.meomi.domain.user.domain.User
import java.time.LocalDate
import javax.persistence.*

@Entity
class Study(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "study_id")
    val id: Long,
    val topic: String,
    val date: LocalDate,
    @ManyToOne
    @JoinColumn(name = "user_id")
    val user: User,
)