package com.project.meomi.domain.user.utils

import com.project.meomi.domain.user.domain.User

interface UserUtil {

    fun currentUser(): User

}