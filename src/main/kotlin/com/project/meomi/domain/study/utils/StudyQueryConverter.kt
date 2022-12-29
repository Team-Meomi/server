package com.project.meomi.domain.study.utils

import com.project.meomi.domain.study.domain.Study
import com.project.meomi.domain.study.domain.StudyPeople
import com.project.meomi.domain.study.presentation.data.dto.StudyPeopleQueryDto
import com.project.meomi.domain.study.presentation.data.dto.StudyPeopleQueryListDto
import com.project.meomi.domain.study.presentation.data.dto.StudyQueryDto
import com.project.meomi.domain.study.presentation.data.dto.StudyQueryListDto
import com.project.meomi.domain.study.presentation.data.response.*

interface StudyQueryConverter {

    fun toQueryDto(study: Study, isMine: Boolean, isStatus: Boolean, studyPeople: List<StudyPeople>): StudyQueryDto
    fun toQueryDto(studyPeople: StudyPeople): StudyPeopleQueryDto
    fun toQueryListDto(study: Study): StudyQueryListDto
    fun toQueryListDto(dto: List<StudyPeopleQueryDto>): StudyPeopleQueryListDto
    fun toResponse(dto: StudyQueryDto): StudyResponse
    fun toResponse(isStatus: Boolean): CheckStudyResponse
    fun toResponse(dto: List<List<StudyPeopleQueryDto>>): StudyPeopleListResponse
    fun toResponse(dto: List<StudyQueryListDto>): List<StudyListResponse>
    fun toResponse(dto: List<StudyPeopleQueryDto>): StudyPeopleResponse

}