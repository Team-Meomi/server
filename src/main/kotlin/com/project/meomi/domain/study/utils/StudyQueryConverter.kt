package com.project.meomi.domain.study.utils

import com.project.meomi.domain.study.domain.Study
import com.project.meomi.domain.study.domain.StudyPeople
import com.project.meomi.domain.study.presentation.data.dto.StudyQueryDto
import com.project.meomi.domain.study.presentation.data.dto.StudyQueryListDto
import com.project.meomi.domain.study.presentation.data.response.CheckStudyResponse
import com.project.meomi.domain.study.presentation.data.response.StudyListResponse
import com.project.meomi.domain.study.presentation.data.response.StudyResponse

interface StudyQueryConverter {

    fun toQueryDto(study: Study, isMine: Boolean, isStatus: Boolean, studyPeople: List<StudyPeople>): StudyQueryDto
    fun toQueryListDto(study: Study): StudyQueryListDto
    fun toResponse(dto: StudyQueryDto): StudyResponse
    fun toResponse(isStatus: Boolean): CheckStudyResponse
    fun toResponse(dto: List<StudyQueryListDto>): List<StudyListResponse>

}