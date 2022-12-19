package com.project.meomi.domain.conference.presentation

import com.project.meomi.domain.conference.presentation.data.request.ConferenceRentRequest
import com.project.meomi.domain.conference.presentation.data.request.CreateConferenceRequest
import com.project.meomi.domain.conference.presentation.data.request.UpdateConferenceRequest
import com.project.meomi.domain.conference.presentation.data.response.CheckConferenceResponse
import com.project.meomi.domain.conference.presentation.data.response.ConferenceResponse
import com.project.meomi.domain.conference.service.ConferenceQueryService
import com.project.meomi.domain.conference.service.ConferenceService
import com.project.meomi.domain.conference.utils.ConferenceConverter
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("api/v1/user/conference/")
class ConferenceController(
    private val conferenceConverter: ConferenceConverter,
    private val conferenceService: ConferenceService,
    private val conferenceQueryService: ConferenceQueryService
) {

    @PostMapping
    fun createConference(@RequestBody @Valid request: CreateConferenceRequest): ResponseEntity<Void> =
        conferenceConverter.toDto(request)
            .let { conferenceService.createConference(it) }
            .let { ResponseEntity.status(HttpStatus.CREATED).build() }

    @PatchMapping("{id}")
    fun updateConference(
        @PathVariable id: Long,
        @RequestBody @Valid request: UpdateConferenceRequest
    ): ResponseEntity<Void> =
        conferenceConverter.toDto(request, id)
            .let { conferenceService.updateConference(it) }
            .let { ResponseEntity.ok().build() }

    @DeleteMapping("{id}")
    fun deleteConference(@PathVariable id: Long): ResponseEntity<Void> =
        conferenceConverter.toDto(id)
            .let { conferenceService.deleteConference(it) }
            .let { ResponseEntity.ok().build() }

    @PostMapping("check")
    fun checkAudiovisualRoomIsRent(@RequestBody request: ConferenceRentRequest): ResponseEntity<CheckConferenceResponse> =
        conferenceConverter.toDto(request)
            .let { conferenceQueryService.checkConferenceIsRent(it) }
            .let { conferenceConverter.toResponse(it) }
            .let { ResponseEntity.ok(it) }

    @GetMapping("{id}")
    fun findConferenceById(@PathVariable id: Long): ResponseEntity<ConferenceResponse> =
        conferenceConverter.toDto(id).let { conferenceQueryService.findConferenceById(it) }
            .let { conferenceConverter.toResponse(it) }.let { ResponseEntity.ok(it) }

    @PostMapping("{id}")
    fun joinConference(@PathVariable id: Long): ResponseEntity<Void> =
        conferenceConverter.toDto(id)
            .let { conferenceService.joinConference(it) }
            .let { ResponseEntity.ok().build() }

    @DeleteMapping("cancel/{id}")
    fun cancelConference(@PathVariable id: Long): ResponseEntity<Void> =
        conferenceConverter.toDto(id)
            .let { conferenceService.cancelConference(it) }
            .let { ResponseEntity.ok().build() }

}