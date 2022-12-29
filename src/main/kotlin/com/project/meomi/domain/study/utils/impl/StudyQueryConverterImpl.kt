package com.project.meomi.domain.study.utils.impl

import com.project.meomi.domain.study.domain.Study
import com.project.meomi.domain.study.domain.StudyPeople
import com.project.meomi.domain.study.presentation.data.dto.StudyPeopleQueryDto
import com.project.meomi.domain.study.presentation.data.dto.StudyPeopleQueryListDto
import com.project.meomi.domain.study.presentation.data.dto.StudyQueryDto
import com.project.meomi.domain.study.presentation.data.dto.StudyQueryListDto
import com.project.meomi.domain.study.presentation.data.response.*
import com.project.meomi.domain.study.utils.StudyQueryConverter
import org.springframework.stereotype.Component

@Component
class StudyQueryConverterImpl: StudyQueryConverter {

    override fun toQueryDto(study: Study, isMine: Boolean, isStatus: Boolean, studyPeople: List<StudyPeople>): StudyQueryDto =
        StudyQueryDto(
            id = study.id,
            title = study.title,
            content = study.content,
            category = study.category,
            date = study.date,
            studyType = study.studyType,
            isMine = isMine,
            isStatus = isStatus,
            writer = StudyQueryDto.UserDto(
                study.user.id,
                study.user.stuNum,
                study.user.name
            ),
            count = StudyQueryDto.CountDto(study.count, study.maxCount),
            list = studyPeople.map {
                StudyQueryDto.UserDto(
                    it.user.id,
                    it.user.stuNum,
                    it.user.name
                )
            }
        )

    override fun toQueryDto(studyPeople: StudyPeople): StudyPeopleQueryDto =
        StudyPeopleQueryDto(
            id = studyPeople.user.id,
            stuNum = studyPeople.user.stuNum,
            name = studyPeople.user.name
        )

    override fun toQueryListDto(study: Study): StudyQueryListDto =
        StudyQueryListDto(
            id = study.id,
            title = study.title,
            category = study.category,
            date = study.date,
            studyType = study.studyType
        )

    override fun toQueryListDto(dto: List<StudyPeopleQueryDto>): StudyPeopleQueryListDto =
        StudyPeopleQueryListDto(list = dto)

    override fun toResponse(dto: StudyQueryDto): StudyResponse =
        StudyResponse(
            id = dto.id,
            title = dto.title,
            content = dto.content,
            category = dto.category,
            date = dto.date,
            studyType = dto.studyType,
            isMine = dto.isMine,
            isStatus = dto.isStatus,
            writer = dto.writer,
            count = dto.count,
            list = dto.list
        )

    override fun toResponse(isStatus: Boolean): CheckStudyResponse =
        CheckStudyResponse(isStatus = isStatus)

    override fun toResponse(dto: List<List<StudyPeopleQueryDto>>): StudyPeopleListResponse =
        StudyPeopleListResponse(dto)

    override fun toResponse(dto: List<StudyQueryListDto>): List<StudyListResponse> =
        dto.map { StudyListResponse(it.id, it.title, it.category, it.date, it.studyType) }

    override fun toResponse(dto: List<StudyPeopleQueryDto>): StudyPeopleResponse =
        StudyPeopleResponse(dto)

}