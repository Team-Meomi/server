package com.project.meomi.domain.conference.domain

import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne

@Entity
class ConferenceCount(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "conference_count_id")
    val id: Long,
    var count: Long,
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "conference_id")
    val conference: Conference
){
    fun addCount() {
        this.count++
    }

    fun removeCount() {
        this.count--
    }
}
