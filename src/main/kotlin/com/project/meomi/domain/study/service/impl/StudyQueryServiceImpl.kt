package com.project.meomi.domain.study.service.impl

import com.project.meomi.domain.study.domain.repository.StudyPeopleRepository
import com.project.meomi.domain.study.domain.repository.StudyRepository
import com.project.meomi.domain.study.exception.CannotRentAudiovisualRentException
import com.project.meomi.domain.study.exception.CannotRentHomeBaselRentException
import com.project.meomi.domain.study.exception.StudyNotFountException
import com.project.meomi.domain.study.presentation.data.dto.StudyDto
import com.project.meomi.domain.study.presentation.data.dto.StudyKeywordDto
import com.project.meomi.domain.study.presentation.data.dto.StudyQueryDto
import com.project.meomi.domain.study.presentation.data.dto.StudyQueryListDto
import com.project.meomi.domain.study.service.StudyQueryService
import com.project.meomi.domain.study.utils.StudyQueryConverter
import com.project.meomi.domain.user.utils.UserUtil
import com.project.meomi.global.annotation.TransactionWithReadOnly
import org.springframework.stereotype.Service

@Service
class StudyQueryServiceImpl(
    private val studyRepository: StudyRepository,
    private val studyPeopleRepository: StudyPeopleRepository,
    private val studyQueryConverter: StudyQueryConverter,
    private val userUtil: UserUtil
): StudyQueryService {

    @TransactionWithReadOnly
    override fun checkAudiovisualIsRent(dto: StudyDto) {
        if (studyRepository.existsByDateAndStudyType(dto.date, "컨퍼런스")) {
            throw CannotRentAudiovisualRentException()
        }
    }

    @TransactionWithReadOnly
    override fun checkHomeBaseIsRent(dto: StudyDto) {
        if (studyRepository.findStudyByDateAndStudyType(dto.date, "스터디").size >= 3) {
            throw CannotRentHomeBaselRentException()
        }
    }

    @TransactionWithReadOnly
    override fun findStudyById(dto: StudyDto): StudyQueryDto {
        val study = studyRepository.findStudyById(dto.id)
            ?: throw StudyNotFountException()

        val isStatus = studyPeopleRepository.existsByStudyIdAndUserId(study.id, userUtil.currentUser().id)
        val studyPeople = studyPeopleRepository.findStudyPeopleByStudyId(dto.id)

        return studyQueryConverter.toQueryDto(study, isStudyMine(study.user.id), isStatus, studyPeople)
    }

    @TransactionWithReadOnly
    override fun findAllStudies(): List<StudyQueryListDto> =
        studyRepository.findAllByOrderByCreateAtDesc()
            .map { studyQueryConverter.toQueryListDto(it) }

    @TransactionWithReadOnly
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

    private fun isStudyMine(studyWriterId: Long) =
        userUtil.currentUser().id == studyWriterId

}