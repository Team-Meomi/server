package com.project.meomi.domain.study.service

import com.project.meomi.domain.study.presentation.data.dto.StudyPeopleDto
import com.project.meomi.domain.study.presentation.data.dto.StudyPeopleQueryDto

interface AdminStudyQueryService {

    fun findAudiovisualPeople(): List<StudyPeopleQueryDto>
    fun findHomebasePeople(): List<List<StudyPeopleQueryDto>>
    fun searchAudiovisualPeople(dto: StudyPeopleDto): List<StudyPeopleQueryDto>
    fun searchHomebasePeople(dto: StudyPeopleDto): List<List<StudyPeopleQueryDto>>

}