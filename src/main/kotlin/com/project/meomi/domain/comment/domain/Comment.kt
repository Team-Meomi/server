package com.project.meomi.domain.comment.domain

import com.project.meomi.domain.study.domain.Study
import com.project.meomi.domain.user.domain.User
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.ManyToOne

@Entity
class Comment(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    val id: Long,
    var comment: String,
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    val study: Study,
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    val user: User,
) {
    fun updateComment(comment: String) {
        this.comment = comment
    }
}