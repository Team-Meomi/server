package com.project.meomi.domain.study.service

import com.project.meomi.domain.study.presentation.data.dto.*

interface StudyQueryService {

    fun checkHomeBaseIsRent(dto: StudyDto): Boolean
    fun findStudyById(dto: StudyDto): StudyQueryDto

}