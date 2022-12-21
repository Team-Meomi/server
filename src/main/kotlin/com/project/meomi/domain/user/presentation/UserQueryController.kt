package com.project.meomi.domain.user.presentation

import com.project.meomi.domain.study.presentation.data.response.StudyListResponse
import com.project.meomi.domain.study.utils.StudyQueryConverter
import com.project.meomi.domain.user.presentation.data.response.UserInfoResponse
import com.project.meomi.domain.user.service.UserQueryService
import com.project.meomi.domain.user.utils.UserConverter
import com.project.meomi.domain.user.utils.UserQueryConverter
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/v1/user/")
class UserQueryController(
    private val userConverter: UserConverter,
    private val userQueryConverter: UserQueryConverter,
    private val userQueryService: UserQueryService,
    private val studyQueryConverter: StudyQueryConverter
) {

    @GetMapping
    fun findMyInfo(): ResponseEntity<UserInfoResponse> =
        userQueryService.findMyInfo()
            .let { userQueryConverter.toResponse(it) }
            .let { ResponseEntity.ok(it) }

    @GetMapping("{id}")
    fun findUserInfo(@PathVariable id: Long): ResponseEntity<UserInfoResponse> =
        userConverter.toDto(id)
            .let { userQueryService.findUserInfo(it) }
            .let { userQueryConverter.toResponse(it) }
            .let { ResponseEntity.ok(it) }

    @GetMapping("written/{id}")
    fun findWrittenStudy(@PathVariable id: Long): ResponseEntity<List<StudyListResponse>> =
        userConverter.toDto(id)
            .let { userQueryService.findWrittenStudy(it) }
            .let { studyQueryConverter.toResponse(it) }
            .let { ResponseEntity.ok(it) }

    @GetMapping("joined/{id}")
    fun findJoinedStudy(@PathVariable id: Long): ResponseEntity<List<StudyListResponse>> =
        userConverter.toDto(id)
            .let { userQueryService.findJoinedStudy(it) }
            .let { studyQueryConverter.toResponse(it) }
            .let { ResponseEntity.ok(it) }

}