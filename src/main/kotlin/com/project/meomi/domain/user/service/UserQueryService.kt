package com.project.meomi.domain.user.service

import com.project.meomi.domain.study.presentation.data.dto.StudyQueryListDto
import com.project.meomi.domain.user.presentation.data.dto.UserDto
import com.project.meomi.domain.user.presentation.data.dto.UserQueryDto

interface UserQueryService {

    fun findMyInfo(): UserQueryDto
    fun findUserInfo(dto: UserDto): UserQueryDto
    fun findWrittenStudy(dto: UserDto): List<StudyQueryListDto>
    fun findJoinedStudy(dto: UserDto): List<StudyQueryListDto>

}