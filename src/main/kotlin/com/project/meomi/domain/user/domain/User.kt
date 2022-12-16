package com.project.meomi.domain.user.domain

import com.project.meomi.domain.user.presentation.data.type.Gender
import com.project.meomi.domain.user.presentation.data.type.Role
import javax.persistence.*

@Entity
class User(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    val id: Long,
    val email: String,
    val password: String,
    val name: String,
    val stuNum: String,
    @Enumerated(EnumType.STRING)
    val gender: Gender,
    @Enumerated(EnumType.STRING)
    @ElementCollection
    @CollectionTable(name = "roles", joinColumns = [JoinColumn(name = "user_id")])
    val roles: MutableList<Role>,
    var refreshToken: String
) {
    fun updateRefreshToken(refreshToken: String) {
        this.refreshToken = refreshToken
    }
}