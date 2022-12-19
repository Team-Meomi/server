package com.project.meomi.domain.study.service.impl

import com.project.meomi.domain.study.domain.repository.StudyPeopleRepository
import com.project.meomi.domain.study.domain.repository.StudyRepository
import com.project.meomi.domain.study.exception.StudyNotFountException
import com.project.meomi.domain.study.presentation.data.dto.*
import com.project.meomi.domain.study.service.StudyQueryService
import com.project.meomi.domain.study.utils.StudyConverter
import com.project.meomi.domain.user.utils.UserUtil
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.lang.Exception

@Service
class StudyQueryServiceImpl(
    private val studyRepository: StudyRepository,
    private val studyPeopleRepository: StudyPeopleRepository,
    private val studyConverter: StudyConverter,
    private val userUtil: UserUtil
): StudyQueryService {

    @Transactional(readOnly = true, rollbackFor = [Exception::class])
    override fun checkHomeBaseIsRent(dto: StudyDto): Boolean =
        !studyRepository.existsStudyByDate(dto.date)

    @Transactional(readOnly = true, rollbackFor = [Exception::class])
    override fun findStudyById(dto: StudyDto): StudyQueryDto {
        val study = studyRepository.findStudyById(dto.id)
            ?: throw StudyNotFountException()

        val isStatus = studyPeopleRepository.existsConferencePeopleByStudyIdAndStudyUserId(study.id, userUtil.currentUser().id)
        val studyPeople = studyPeopleRepository.findStudyPeopleByStudyId(dto.id)

        return studyConverter.toQueryDto(study, isStudyMine(study.user.id), isStatus, studyPeople)
    }

    private fun isStudyMine(studyUserId: Long) =
        userUtil.currentUser().id == studyUserId

}