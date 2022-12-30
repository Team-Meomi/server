package com.project.meomi.domain.study.domain.repository

import com.project.meomi.domain.study.presentation.data.dto.StudyPeopleQueryDto

interface StudyPeopleRepositoryCustom {

    fun findStudyPeopleByStuNum(stuNum: Int, id: Long): List<StudyPeopleQueryDto>
    fun findStudyPeopleByStuName(stuName: String, id: Long): List<StudyPeopleQueryDto>
    fun findStudyPeopleByStuNumAndStuName(stuNum: Int, stuName: String, id: Long): List<StudyPeopleQueryDto>

}