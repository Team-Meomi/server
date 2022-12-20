package com.project.meomi.domain.study.utils.impl

import com.project.meomi.domain.study.domain.Study
import com.project.meomi.domain.study.domain.StudyPeople
import com.project.meomi.domain.study.presentation.data.dto.StudyDto
import com.project.meomi.domain.study.presentation.data.request.CreateStudyRequest
import com.project.meomi.domain.study.presentation.data.request.UpdateStudyRequest
import com.project.meomi.domain.study.utils.StudyConverter
import com.project.meomi.domain.user.domain.User
import org.springframework.stereotype.Component
import java.time.LocalDate

@Component
class StudyConverterImpl: StudyConverter {

    override fun toDto(request: CreateStudyRequest): StudyDto =
        StudyDto(
            id = -1,
            title = request.title,
            content = request.content,
            category = request.category,
            date = request.date,
            count = 0,
            maxCount = request.maxCount,
            studyType = request.studyType
        )

    override fun toDto(id: Long, request: UpdateStudyRequest): StudyDto =
        StudyDto(
            id = id,
            title = request.title,
            content = request.content,
            category = request.category,
            date = request.date,
            count = 0,
            maxCount = request.maxCount,
            studyType = ""
        )

    override fun toDto(id: Long): StudyDto =
        StudyDto(
            id = id,
            title = "",
            content = "",
            category = "",
            date = LocalDate.now(),
            count = 0,
            maxCount = 0,
            studyType = ""
        )

    override fun toDto(date: String): StudyDto  =
        StudyDto(
            id = -1,
            title = "",
            content = "",
            category = "",
            date = LocalDate.parse(date),
            count = 0,
            maxCount = 0,
            studyType = ""
        )

    override fun toEntity(dto: StudyDto, user: User): Study =
        Study(
            id = -1,
            title = dto.title,
            content = dto.content,
            category = dto.category,
            date = dto.date,
            studyType = dto.studyType,
            count = 0,
            maxCount = dto.maxCount,
            user = user,
        )

    override fun toEntity(study: Study, user: User): StudyPeople =
        StudyPeople(
            id = -1,
            study = study,
            user = user
        )

}