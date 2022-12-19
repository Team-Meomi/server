package com.project.meomi.domain.study.utils.impl

import com.project.meomi.domain.study.domain.Study
import com.project.meomi.domain.study.domain.StudyPeople
import com.project.meomi.domain.study.presentation.data.dto.StudyDto
import com.project.meomi.domain.study.presentation.data.dto.StudyQueryDto
import com.project.meomi.domain.study.presentation.data.request.CreateStudyRequest
import com.project.meomi.domain.study.presentation.data.request.UpdateStudyRequest
import com.project.meomi.domain.study.presentation.data.response.CheckStudyResponse
import com.project.meomi.domain.study.presentation.data.response.StudyResponse
import com.project.meomi.domain.study.utils.StudyConverter
import com.project.meomi.domain.user.domain.User
import org.springframework.stereotype.Component
import java.time.LocalDate

@Component
class StudyConverterImpl : StudyConverter {

    override fun toDto(request: CreateStudyRequest): StudyDto =
        StudyDto(
            id = -1,
            title = request.title,
            content = request.content,
            category = request.category,
            date = request.date
        )

    override fun toDto(id: Long, request: UpdateStudyRequest): StudyDto =
        StudyDto(
            id = id,
            title = request.title,
            content = request.content,
            category = request.category,
            date = request.date
        )

    override fun toDto(id: Long): StudyDto =
        StudyDto(
            id = id,
            title = "",
            content = "",
            category = "",
            date = LocalDate.now(),
        )

    override fun toDto(date: String): StudyDto  =
        StudyDto(
            id = -1,
            title = "",
            content = "",
            category = "",
            date = LocalDate.parse(date),
        )

    override fun toEntity(dto: StudyDto, user: User): Study =
        Study(
            id = -1,
            title = dto.title,
            content = dto.content,
            category = dto.category,
            date = dto.date,
            count = 0,
            user = user
        )

    override fun toEntity(study: Study, user: User): StudyPeople =
        StudyPeople(
            id = -1,
            study = study,
            user = user
        )

    override fun toQueryDto(
        study: Study,
        isMine: Boolean,
        isStatus: Boolean,
        studyPeople: List<StudyPeople>
    ): StudyQueryDto =
        StudyQueryDto(
            id = study.id,
            title = study.title,
            content = study.content,
            category = study.category,
            date = study.date,
            isMine = isMine,
            isStatus = isStatus,
            writer = StudyQueryDto.UserResponse(
                study.user.id,
                study.user.stuNum,
                study.user.name,
                study.user.gender
            ),
            count = StudyQueryDto.CountResponse(study.count, 5),
            list = studyPeople.map {
                StudyQueryDto.UserResponse(
                    it.user.id,
                    it.user.stuNum,
                    it.user.name,
                    it.user.gender
                )
            }
        )

    override fun toResponse(dto: StudyQueryDto): StudyResponse =
        StudyResponse(
            id = dto.id,
            title = dto.title,
            content = dto.content,
            category = dto.category,
            date = dto.date,
            isMine = dto.isMine,
            isStatus = dto.isStatus,
            writer = dto.writer,
            count = dto.count,
            list = dto.list
        )

    override fun toResponse(isStatus: Boolean): CheckStudyResponse =
        CheckStudyResponse(isStatus = isStatus)

}