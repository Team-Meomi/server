package com.project.meomi.domain.study.presentation.data.response

import com.project.meomi.domain.study.presentation.data.dto.StudyPeopleQueryDto

data class StudyPeopleListResponse(
    val list: List<List<StudyPeopleQueryDto>>
)