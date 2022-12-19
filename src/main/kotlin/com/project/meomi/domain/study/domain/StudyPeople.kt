package com.project.meomi.domain.study.domain

import com.project.meomi.domain.user.domain.User
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction
import javax.persistence.*

@Entity
class StudyPeople(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "study_people_id")
    val id: Long,
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "study_id")
    val study: Study,
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "user_id")
    val user: User
)