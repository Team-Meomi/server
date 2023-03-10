package com.project.meomi.domain.study.service

import com.project.meomi.domain.study.presentation.data.dto.*

interface StudyQueryService {

    fun checkAudiovisualIsRent(dto: StudyDto)
    fun checkHomeBaseIsRent(dto: StudyDto)
    fun findStudyById(dto: StudyDto): StudyQueryDto
    fun findAllStudies(): List<StudyQueryListDto>
    fun findStudyByKeyword(dto: StudyKeywordDto): List<StudyQueryListDto>

}