package com.project.meomi.domain.study.utils

import com.project.meomi.domain.study.domain.Study
import com.project.meomi.domain.study.presentation.data.dto.StudyDto
import com.project.meomi.domain.study.presentation.data.type.ValidatorType

interface StudyValidator {

    fun validate(studyType: ValidatorType, dto: StudyDto): Study

}