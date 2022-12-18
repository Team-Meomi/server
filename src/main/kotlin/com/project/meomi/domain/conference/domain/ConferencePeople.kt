package com.project.meomi.domain.conference.domain

import com.project.meomi.domain.user.domain.User
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction
import javax.persistence.*

@Entity
class ConferencePeople(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "conference_people_id")
    val id: Long,
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "conference_id")
    val conference: Conference,
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "user_id")
    val user: User
)