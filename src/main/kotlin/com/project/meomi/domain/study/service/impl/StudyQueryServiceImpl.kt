package com.project.meomi.domain.study.service.impl

import com.project.meomi.domain.study.domain.repository.StudyPeopleRepository
import com.project.meomi.domain.study.domain.repository.StudyRepository
import com.project.meomi.domain.study.exception.StudyNotFountException
import com.project.meomi.domain.study.presentation.data.dto.StudyDto
import com.project.meomi.domain.study.presentation.data.dto.StudyKeywordDto
import com.project.meomi.domain.study.presentation.data.dto.StudyQueryDto
import com.project.meomi.domain.study.presentation.data.dto.StudyQueryListDto
import com.project.meomi.domain.study.service.StudyQueryService
import com.project.meomi.domain.study.utils.StudyQueryConverter
import com.project.meomi.domain.user.utils.UserUtil
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class StudyQueryServiceImpl(
    private val studyRepository: StudyRepository,
    private val studyPeopleRepository: StudyPeopleRepository,
    private val studyQueryConverter: StudyQueryConverter,
    private val userUtil: UserUtil
): StudyQueryService {

    @Transactional(readOnly = true, rollbackFor = [Exception::class])
    override fun checkAudiovisualIsRent(dto: StudyDto): Boolean {
        return !studyRepository.existsByDateAndStudyType(dto.date, "컨퍼런스")
    }

    @Transactional(readOnly = true, rollbackFor = [Exception::class])
    override fun checkHomeBaseIsRent(dto: StudyDto): Boolean =
        !studyRepository.existsByDateAndStudyType(dto.date,"스터디")

    @Transactional(readOnly = true, rollbackFor = [Exception::class])
    override fun findStudyById(dto: StudyDto): StudyQueryDto {
        val study = studyRepository.findStudyById(dto.id)
            ?: throw StudyNotFountException()

        val isStatus = studyPeopleRepository.existsByStudyIdAndUserId(study.id, userUtil.currentUser().id)
        val studyPeople = studyPeopleRepository.findStudyPeopleByStudyId(dto.id)

        return studyQueryConverter.toQueryDto(study, isStudyMine(study.user.id), isStatus, studyPeople)
    }

    @Transactional(readOnly = true, rollbackFor = [Exception::class])
    override fun findAllStudies(): List<StudyQueryListDto> =
        studyRepository.findAllByOrderByCreateAtDesc()
            .map { studyQueryConverter.toQueryListDto(it) }

    @Transactional(readOnly = true, rollbackFor = [Exception::class])
    override fun findStudyByKeyword(dto: StudyKeywordDto): List<StudyQueryListDto> {

        if(dto.title != null && dto.category == "") {
            return studyRepository.findStudyByTitleContainsOrderByCreateAtDesc(dto.title)
                .map{ studyQueryConverter.toQueryListDto(it) }
        }

        if(dto.title == "" && dto.category != null) {
            return studyRepository.findStudyByCategoryOrderByCreateAtDesc(dto.category)
                .map{ studyQueryConverter.toQueryListDto(it) }
        }

        if(dto.title != null && dto.category != null) {
            return studyRepository.findStudyByTitleContainsAndCategoryOrderByCreateAtDesc(dto.title, dto.category)
                .map { studyQueryConverter.toQueryListDto(it) }
        }

        return studyRepository.findAllByOrderByCreateAtDesc()
            .map { studyQueryConverter.toQueryListDto(it) }

    }

    private fun isStudyMine(studyUserId: Long) =
        userUtil.currentUser().id == studyUserId

}