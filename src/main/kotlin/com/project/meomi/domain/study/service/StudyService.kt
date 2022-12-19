package com.project.meomi.domain.study.service

import com.project.meomi.domain.study.presentation.data.dto.StudyDto

interface StudyService {

    fun createStudy(dto: StudyDto)
    fun joinStudy(dto: StudyDto)
    fun cancelStudy(dto: StudyDto)
    fun updateStudy(dto: StudyDto)
    fun deleteStudy(dto: StudyDto)

}