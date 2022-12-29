package com.project.meomi.domain.study.service.impl

import com.project.meomi.domain.study.domain.repository.StudyPeopleRepository
import com.project.meomi.domain.study.domain.repository.StudyRepository
import com.project.meomi.domain.study.presentation.data.dto.StudyPeopleQueryDto
import com.project.meomi.domain.study.service.AdminStudyQueryService
import com.project.meomi.domain.study.utils.StudyQueryConverter
import com.project.meomi.global.annotation.TransactionWithReadOnly
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class AdminStudyQueryServiceImpl(
    private val studyRepository: StudyRepository,
    private val studyPeopleRepository: StudyPeopleRepository,
    private val studyQueryConverter: StudyQueryConverter
): AdminStudyQueryService {

    @TransactionWithReadOnly
    override fun findAudiovisualPeople(): List<StudyPeopleQueryDto> {
        val conference = studyRepository.findStudyByDateAndStudyType(LocalDate.now(), "컨퍼런스")
        return studyPeopleRepository.findStudyPeopleByStudyId(conference[0].id)
            .map { studyQueryConverter.toQueryDto(it) }
    }

    @TransactionWithReadOnly
    override fun findHomebasePeople(): List<List<StudyPeopleQueryDto>> =
        studyRepository.findStudyByDateAndStudyType(LocalDate.now(), "스터디")
            .map {
                studyPeopleRepository.findStudyPeopleByStudyId(it.id)
                    .map { studyPeople -> studyQueryConverter.toQueryDto(studyPeople) }
            }

    @TransactionWithReadOnly
    override fun searchAudiovisualPeople(): List<StudyPeopleQueryDto> {
        TODO("Not yet implemented")
    }

    @TransactionWithReadOnly
    override fun searchHomebasePeople(): List<StudyPeopleQueryDto> {
        TODO("Not yet implemented")
    }

}