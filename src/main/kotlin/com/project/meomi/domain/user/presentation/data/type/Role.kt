package com.project.meomi.domain.user.presentation.data.type

import org.springframework.security.core.GrantedAuthority

enum class Role: GrantedAuthority {

    ROLE_USER, ROLE_ADMIN;

    override fun getAuthority(): String = name

}