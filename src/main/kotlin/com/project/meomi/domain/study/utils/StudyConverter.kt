package com.project.meomi.domain.study.utils

import com.project.meomi.domain.study.domain.Study
import com.project.meomi.domain.study.domain.StudyPeople
import com.project.meomi.domain.study.presentation.data.dto.StudyDto
import com.project.meomi.domain.study.presentation.data.dto.StudyKeywordDto
import com.project.meomi.domain.study.presentation.data.request.CreateStudyRequest
import com.project.meomi.domain.study.presentation.data.request.UpdateStudyRequest
import com.project.meomi.domain.user.domain.User

interface StudyConverter {

    fun toDto(request: CreateStudyRequest): StudyDto
    fun toDto(id: Long, request: UpdateStudyRequest): StudyDto
    fun toDto(id: Long): StudyDto
    fun toDto(date: String): StudyDto
    fun toDto(title: String, category: String): StudyKeywordDto
    fun toEntity(dto: StudyDto, user: User): Study
    fun toEntity(study: Study, user: User): StudyPeople

}