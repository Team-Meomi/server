package com.project.meomi.domain.study.presentation

import com.project.meomi.domain.study.presentation.data.response.StudyPeopleListResponse
import com.project.meomi.domain.study.presentation.data.response.StudyPeopleResponse
import com.project.meomi.domain.study.service.AdminStudyQueryService
import com.project.meomi.domain.study.service.StudyQueryService
import com.project.meomi.domain.study.utils.StudyQueryConverter
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/v1/admin/study/")
class AdminStudyController(
    private val adminStudyQueryService: AdminStudyQueryService,
    private val studyQueryConverter: StudyQueryConverter
) {

    @GetMapping("audiovisual")
    fun findAudiovisualPeople(): ResponseEntity<StudyPeopleResponse> =
        adminStudyQueryService.findAudiovisualPeople()
            .let { studyQueryConverter.toResponse(it) }
            .let { ResponseEntity.ok(it) }

    @GetMapping("homebase")
    fun findHomebasePeople(): ResponseEntity<StudyPeopleListResponse> =
        adminStudyQueryService.findHomebasePeople()
            .let { studyQueryConverter.toResponse(it) }
            .let { ResponseEntity.ok(it) }

    @GetMapping("audiovisual/search")
    fun searchAudiovisualPeople(): ResponseEntity<StudyPeopleResponse> =
        adminStudyQueryService.findAudiovisualPeople()
            .let { studyQueryConverter.toResponse(it) }
            .let { ResponseEntity.ok(it) }

    @GetMapping("homebase/search")
    fun searchHomebasePeople(): ResponseEntity<StudyPeopleListResponse> =
        adminStudyQueryService.findHomebasePeople()
            .let { studyQueryConverter.toResponse(it) }
            .let { ResponseEntity.ok(it) }

}