package com.project.meomi.domain.study.service.impl

import com.project.meomi.domain.study.domain.repository.StudyPeopleRepository
import com.project.meomi.domain.study.domain.repository.StudyRepository
import com.project.meomi.domain.study.exception.StudyNotFountException
import com.project.meomi.domain.study.presentation.data.dto.StudyDto
import com.project.meomi.domain.study.presentation.data.type.ApplicantValidatorType
import com.project.meomi.domain.study.presentation.data.type.CreateValidatorType
import com.project.meomi.domain.study.service.StudyService
import com.project.meomi.domain.study.utils.StudyConverter
import com.project.meomi.domain.study.utils.StudyValidator
import com.project.meomi.domain.user.utils.UserUtil
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class StudyServiceImpl(
    private val studyRepository: StudyRepository,
    private val studyPeopleRepository: StudyPeopleRepository,
    private val studyConverter: StudyConverter,
    private val userUtil: UserUtil,
    private val studyValidator: StudyValidator
): StudyService {

    @Transactional(rollbackFor = [Exception::class])
    override fun createStudy(dto: StudyDto) {
        studyValidator.createValidate(CreateValidatorType.findName(dto.studyType), dto)
            .let { studyConverter.toEntity(dto, userUtil.currentUser()) }
            .let { studyRepository.save(it) }
    }

    @Transactional(rollbackFor = [Exception::class])
    override fun joinStudy(dto: StudyDto) =
        studyValidator.applicantValidate(ApplicantValidatorType.JOIN, dto)
            .let { studyConverter.toEntity(it, userUtil.currentUser()) }
            .let { studyPeopleRepository.save(it) }.study.addCount()

    @Transactional(rollbackFor = [Exception::class])
    override fun cancelStudy(dto: StudyDto) {
        studyValidator.applicantValidate(ApplicantValidatorType.CANCEL, dto)
            .let {
                it.removeCount()
                studyPeopleRepository.deleteStudyPeopleByStudyIdAndUserId(it.id, userUtil.currentUser().id)
            }
    }

    @Transactional(rollbackFor = [Exception::class])
    override fun updateStudy(dto: StudyDto) =
        studyRepository.findStudyById(dto.id)
            .let { it ?: throw StudyNotFountException() }
            .let { it.updateStudy(dto, it.count) }

    @Transactional(rollbackFor = [Exception::class])
    override fun deleteStudy(dto: StudyDto) =
        studyRepository.findStudyById(dto.id)
            .let { it ?: throw StudyNotFountException() }
            .let { studyRepository.delete(it) }

}