package com.project.meomi.domain.study.utils.impl

import com.project.meomi.domain.study.domain.Study
import com.project.meomi.domain.study.domain.repository.StudyPeopleRepository
import com.project.meomi.domain.study.domain.repository.StudyRepository
import com.project.meomi.domain.study.exception.ApplicantNotFountException
import com.project.meomi.domain.study.exception.DuplicateApplicantException
import com.project.meomi.domain.study.exception.StudyCountOverException
import com.project.meomi.domain.study.exception.StudyNotFountException
import com.project.meomi.domain.study.presentation.data.dto.StudyDto
import com.project.meomi.domain.study.presentation.data.type.ValidatorType
import com.project.meomi.domain.study.utils.StudyValidator
import com.project.meomi.domain.user.utils.UserUtil
import org.springframework.stereotype.Component

@Component
class StudyValidatorImpl(
    private val studyRepository: StudyRepository,
    private val studyPeopleRepository: StudyPeopleRepository,
    private val userUtil: UserUtil
): StudyValidator {

    override fun validate(validatorType: ValidatorType, dto: StudyDto): Study =
        when(validatorType) {
            ValidatorType.JOIN -> validateJoinStudy(dto)
            ValidatorType.CANCEL -> validateCancelStudy(dto)
        }


    private fun validateJoinStudy(dto: StudyDto): Study =
        studyRepository.findStudyById(dto.id)
            .let { it ?: throw StudyNotFountException() }
            .let {
                isSmallerThanMaxCount(it.count, it.maxCount)
                if(studyPeopleRepository.existsByStudyIdAndUserId(dto.id, userUtil.currentUser().id)) {
                    throw DuplicateApplicantException()
                }
                return it
            }

    private fun validateCancelStudy(dto: StudyDto): Study =
        studyRepository.findStudyById(dto.id)
            .let { it ?: throw StudyNotFountException() }
            .let {
                if(!studyPeopleRepository.existsByStudyIdAndUserId(dto.id, userUtil.currentUser().id)) {
                    throw ApplicantNotFountException()
                }
                return it
            }

    private fun isSmallerThanMaxCount(count: Int, maxCount: Int): Boolean {
        if(count >= maxCount) throw StudyCountOverException()
        return true
    }

}