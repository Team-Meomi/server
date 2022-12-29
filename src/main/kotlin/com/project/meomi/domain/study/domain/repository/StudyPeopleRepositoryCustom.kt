package com.project.meomi.domain.study.domain.repository

import com.project.meomi.domain.study.domain.StudyPeople

interface StudyPeopleRepositoryCustom {

    fun findStudyPeopleByStuNum(stuNum: Int): List<StudyPeople>

}