package com.project.meomi.domain.study.service

import com.project.meomi.domain.study.presentation.data.dto.StudyPeopleQueryDto

interface AdminStudyQueryService {

    fun findAudiovisualPeople(): List<StudyPeopleQueryDto>
    fun findHomebasePeople(): List<List<StudyPeopleQueryDto>>
    fun searchAudiovisualPeople(): List<StudyPeopleQueryDto>
    fun searchHomebasePeople(): List<StudyPeopleQueryDto>

}