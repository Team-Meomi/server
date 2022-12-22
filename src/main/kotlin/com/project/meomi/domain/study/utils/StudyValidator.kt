package com.project.meomi.domain.study.utils

import com.project.meomi.domain.study.domain.Study
import com.project.meomi.domain.study.presentation.data.dto.StudyDto
import com.project.meomi.domain.study.presentation.data.type.ApplicantValidatorType
import com.project.meomi.domain.study.presentation.data.type.CreateValidatorType

interface StudyValidator {

    fun createValidate(validatorType: CreateValidatorType, dto: StudyDto)
    fun applicantValidate(validatorType: ApplicantValidatorType, dto: StudyDto): Study

}