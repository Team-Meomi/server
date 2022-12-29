package com.project.meomi.domain.study.presentation

import com.project.meomi.domain.study.presentation.data.request.CreateStudyRequest
import com.project.meomi.domain.study.presentation.data.request.UpdateStudyRequest
import com.project.meomi.domain.study.presentation.data.response.CheckStudyResponse
import com.project.meomi.domain.study.presentation.data.response.StudyListResponse
import com.project.meomi.domain.study.presentation.data.response.StudyResponse
import com.project.meomi.domain.study.service.StudyQueryService
import com.project.meomi.domain.study.service.StudyService
import com.project.meomi.domain.study.utils.StudyConverter
import com.project.meomi.domain.study.utils.StudyQueryConverter
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("api/v1/user/study/")
class StudyController(
    private val studyService: StudyService,
    private val studyQueryService: StudyQueryService,
    private val studyConverter: StudyConverter,
    private val studyQueryConverter: StudyQueryConverter
) {

    @PostMapping
    fun createStudy(@RequestBody @Valid createStudyRequest: CreateStudyRequest): ResponseEntity<Void> =
        studyConverter.toDto(createStudyRequest)
            .let { studyService.createStudy(it) }
            .let { ResponseEntity.status(HttpStatus.CREATED).build() }

    @PatchMapping("{id}")
    fun updateStudy(
        @PathVariable id: Long,
        @RequestBody @Valid updateStudyRequest: UpdateStudyRequest
    ): ResponseEntity<Void> =
        studyConverter.toDto(id, updateStudyRequest)
            .let { studyService.updateStudy(it) }
            .let { ResponseEntity.ok().build() }

    @DeleteMapping("{id}")
    fun deleteStudy(@PathVariable id: Long): ResponseEntity<Void> =
        studyConverter.toDto(id)
            .let { studyService.deleteStudy(it) }
            .let { ResponseEntity.ok().build() }

    @GetMapping("{id}")
    fun findStudyById(@PathVariable id: Long): ResponseEntity<StudyResponse> =
        studyConverter.toDto(id)
            .let { studyQueryService.findStudyById(it) }
            .let { studyQueryConverter.toResponse(it) }
            .let { ResponseEntity.ok(it) }

    @PostMapping("homebase")
    fun checkHomeBaseIsRent(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) date: String): ResponseEntity<CheckStudyResponse> =
        studyConverter.toDto(date)
            .let { studyQueryService.checkHomeBaseIsRent(it) }
            .let { ResponseEntity.ok().build() }

    @PostMapping("audiovisual")
    fun checkAudiovisualIsRent(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) date: String): ResponseEntity<CheckStudyResponse> =
        studyConverter.toDto(date)
            .let { studyQueryService.checkAudiovisualIsRent(it) }
            .let { ResponseEntity.ok().build() }

    @PostMapping("{id}")
    fun joinStudy(@PathVariable id: Long): ResponseEntity<Void> =
        studyConverter.toDto(id)
            .let { studyService.joinStudy(it) }
            .let { return ResponseEntity.ok().build() }

    @DeleteMapping("cancel/{id}")
    fun cancelStudy(@PathVariable id: Long): ResponseEntity<Void> =
        studyConverter.toDto(id)
            .let { studyService.cancelStudy(it) }
            .let { ResponseEntity.ok().build() }

    @GetMapping
    fun findAllStudies(): ResponseEntity<List<StudyListResponse>> =
        studyQueryService.findAllStudies()
            .let { studyQueryConverter.toResponse(it) }
            .let { ResponseEntity.ok(it) }

    @GetMapping("search")
    fun findStudyByKeyword(
        @RequestParam(required = false) title: String,
        @RequestParam(required = false) category: String
    ): ResponseEntity<List<StudyListResponse>> =
        studyConverter.toDto(title, category)
            .let { studyQueryService.findStudyByKeyword(it) }
            .let { studyQueryConverter.toResponse(it) }
            .let { ResponseEntity.ok(it) }

}
