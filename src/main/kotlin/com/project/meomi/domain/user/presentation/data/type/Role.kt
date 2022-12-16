package com.project.meomi.domain.user.presentation.data.type

import org.springframework.security.core.GrantedAuthority

enum class Role: GrantedAuthority {
    ADMIN, USER;

    override fun getAuthority(): String {
        return name
    }
}