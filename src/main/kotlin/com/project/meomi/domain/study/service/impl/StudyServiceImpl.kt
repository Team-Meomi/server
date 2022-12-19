package com.project.meomi.domain.study.service.impl

import com.project.meomi.domain.study.domain.repository.StudyPeopleRepository
import com.project.meomi.domain.study.domain.repository.StudyRepository
import com.project.meomi.domain.study.exception.DuplicateStudyApplicantException
import com.project.meomi.domain.study.exception.StudyCountOverException
import com.project.meomi.domain.study.exception.StudyNotFountException
import com.project.meomi.domain.study.presentation.data.dto.StudyDto
import com.project.meomi.domain.study.service.StudyService
import com.project.meomi.domain.study.utils.StudyConverter
import com.project.meomi.domain.user.utils.UserUtil
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class StudyServiceImpl(
    private val studyRepository: StudyRepository,
    private val studyPeopleRepository: StudyPeopleRepository,
    private val studyConverter: StudyConverter,
    private val userUtil: UserUtil
): StudyService {

    @Transactional(rollbackFor = [Exception::class])
    override fun createStudy(dto: StudyDto) {
        studyConverter.toEntity(dto, userUtil.currentUser())
            .let { studyRepository.save(it) }
    }

    @Transactional(rollbackFor = [Exception::class])
    override fun joinStudy(dto: StudyDto) {
        val study = studyRepository.findStudyById(dto.id)
            ?: throw StudyNotFountException()

        isSmallerThanFive(study.count)
        if(studyPeopleRepository.existsConferencePeopleByStudyIdAndStudyUserId(study.id, userUtil.currentUser().id)) {
            throw DuplicateStudyApplicantException()
        }

        studyConverter.toEntity(study, userUtil.currentUser())
            .let { studyPeopleRepository.save(it) }
        study.addCount()
    }

    @Transactional(rollbackFor = [Exception::class])
    override fun cancelStudy(dto: StudyDto) {
        val study = studyRepository.findStudyById(dto.id)
            ?: throw StudyNotFountException()
        study.removeCount()
        studyPeopleRepository.deleteStudyPeopleByStudyId(study.id)
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

    private fun isSmallerThanFive(count: Int): Boolean {
        if(count >= 5) throw StudyCountOverException()
        return true
    }

}