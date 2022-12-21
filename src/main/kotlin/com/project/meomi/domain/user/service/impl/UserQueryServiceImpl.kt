package com.project.meomi.domain.user.service.impl

import com.project.meomi.domain.study.domain.repository.StudyRepository
import com.project.meomi.domain.study.presentation.data.dto.StudyQueryListDto
import com.project.meomi.domain.study.utils.StudyQueryConverter
import com.project.meomi.domain.user.domain.repository.UserRepository
import com.project.meomi.domain.user.exception.UserNotFoundException
import com.project.meomi.domain.user.presentation.data.dto.UserDto
import com.project.meomi.domain.user.presentation.data.dto.UserQueryDto
import com.project.meomi.domain.user.service.UserQueryService
import com.project.meomi.domain.user.utils.UserQueryConverter
import com.project.meomi.domain.user.utils.UserUtil
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserQueryServiceImpl(
    private val userRepository: UserRepository,
    private val studyRepository: StudyRepository,
    private val userQueryConverter: UserQueryConverter,
    private val studyQueryConverter: StudyQueryConverter,
    private val userUtil: UserUtil
): UserQueryService {

    @Transactional(readOnly = true, rollbackFor = [Exception::class])
    override fun findMyInfo(): UserQueryDto =
        userUtil.currentUser()
            .let { userQueryConverter.toQueryDto(it) }

    @Transactional(readOnly = true, rollbackFor = [Exception::class])
    override fun findUserInfo(dto: UserDto): UserQueryDto =
        userRepository.findUserById(dto.id)
            .let { it ?: throw UserNotFoundException() }
            .let { userQueryConverter.toQueryDto(it) }

    @Transactional(readOnly = true, rollbackFor = [Exception::class])
    override fun findWrittenStudy(dto: UserDto) =
        studyRepository.findStudyByUserIdOrderByCreateAtDesc(dto.id)
            .map { studyQueryConverter.toQueryListDto(it) }

    @Transactional(readOnly = true, rollbackFor = [Exception::class])
    override fun findJoinedStudy(dto: UserDto): List<StudyQueryListDto> =
        studyRepository.findJoinStudyByUserId(dto.id)
            .map { studyQueryConverter.toQueryListDto(it) }

}