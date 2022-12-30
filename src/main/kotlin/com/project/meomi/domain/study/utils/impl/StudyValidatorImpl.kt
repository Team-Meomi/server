package com.project.meomi.domain.study.utils.impl

import com.project.meomi.domain.comment.domain.repository.CommentRepository
import com.project.meomi.domain.study.domain.Study
import com.project.meomi.domain.study.domain.repository.StudyPeopleRepository
import com.project.meomi.domain.study.domain.repository.StudyRepository
import com.project.meomi.domain.study.exception.*
import com.project.meomi.domain.study.presentation.data.dto.StudyDto
import com.project.meomi.domain.study.presentation.data.type.ApplicantValidatorType
import com.project.meomi.domain.study.presentation.data.type.CreateValidatorType
import com.project.meomi.domain.study.utils.StudyValidator
import com.project.meomi.domain.user.utils.UserUtil
import org.springframework.stereotype.Component
import java.time.LocalDate

@Component
class StudyValidatorImpl(
    private val studyRepository: StudyRepository,
    private val studyPeopleRepository: StudyPeopleRepository,
    private val commentRepository: CommentRepository,
    private val userUtil: UserUtil
) : StudyValidator {

    override fun createValidate(validatorType: CreateValidatorType, dto: StudyDto) {
        when (validatorType) {
            CreateValidatorType.CONFERENCE -> validateCreateConference(dto)
            CreateValidatorType.STUDY -> validateCreateStudy(dto)
        }
    }

    override fun applicantValidate(validatorType: ApplicantValidatorType, dto: StudyDto): Study =
        when (validatorType) {
            ApplicantValidatorType.JOIN -> validateJoinStudy(dto)
            ApplicantValidatorType.CANCEL -> validateCancelStudy(dto)
        }

    private fun validateCreateConference(dto: StudyDto) {
        if (studyRepository.existsByDateAndStudyType(dto.date, "컨퍼런스")) {
            throw CannotRentAudiovisualRentException()
        }
    }

    private fun validateCreateStudy(dto: StudyDto) {
        if (studyRepository.findStudyByDateAndStudyType(dto.date, "스터디").size >= 3) {
            throw CannotRentHomeBaselRentException()
        }
    }

    private fun validateJoinStudy(dto: StudyDto): Study =
        studyRepository.findStudyById(dto.id)
            .let { it ?: throw StudyNotFountException() }
            .let {
                existDuplicateStudy()
                isSmallerThanMaxCount(it.count, it.maxCount)
                if (studyPeopleRepository.existsByStudyIdAndUserId(dto.id, userUtil.currentUser().id)) {
                    throw DuplicateApplicantException()
                }
                return it
            }

    private fun validateCancelStudy(dto: StudyDto): Study =
        studyRepository.findStudyById(dto.id)
            .let { it ?: throw StudyNotFountException() }
            .let {
                if (!studyPeopleRepository.existsByStudyIdAndUserId(dto.id, userUtil.currentUser().id)) {
                    throw ApplicantNotFountException()
                }
                if (studyPeopleRepository.findStudyPeopleByStudyId(it.id).size == 1) {
                    commentRepository.deleteCommentByStudyId(it.id)
                }
                return it
            }

    private fun isSmallerThanMaxCount(count: Int, maxCount: Int): Boolean {
        if (count >= maxCount) throw FullStudyCountException()
        return true
    }

    private fun existDuplicateStudy() {
        if(studyPeopleRepository.existsStudyPeopleByUserIdAndStudyDate(userUtil.currentUser().id, LocalDate.now())) {
            throw DuplicateStudyDateException()
        }
    }

}