package com.project.meomi.domain.study.service.impl

import com.project.meomi.domain.study.domain.repository.StudyPeopleRepository
import com.project.meomi.domain.study.domain.repository.StudyRepository
import com.project.meomi.domain.study.presentation.data.dto.StudyPeopleDto
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
    override fun searchAudiovisualPeople(dto: StudyPeopleDto): List<StudyPeopleQueryDto> {
        val conference = studyRepository.findStudyByDateAndStudyType(LocalDate.now(), "컨퍼런스")

        // 학번을 안보냈을 경우
        dto.stuNum ?: return studyPeopleRepository.findStudyPeopleByStuName(dto.stuName!!, conference[0].id)

        // 이름을 안보냈을 경우
        dto.stuName ?: return studyPeopleRepository.findStudyPeopleByStuNum(dto.stuNum, conference[0].id)

        // 둘다 보냈을 경우
        return studyPeopleRepository.findStudyPeopleByStuNumAndStuName(dto.stuNum, dto.stuName, conference[0].id)
    }


    @TransactionWithReadOnly
    override fun searchHomebasePeople(dto: StudyPeopleDto): List<List<StudyPeopleQueryDto>> =
        studyRepository.findStudyByDateAndStudyType(LocalDate.now(), "스터디")
            .map {

                // 이름만 보냈을 경우
                if (dto.stuNum == null && dto.stuName!!.isNotBlank()) {
                    return@map studyPeopleRepository.findStudyPeopleByStuName(dto.stuName, it.id)
                }

                // 학번만 보냈을 경우
                dto.stuNum ?: studyPeopleRepository.findStudyPeopleByStuName(dto.stuName!!, it.id)

                // 둘다 보냈을 경우
                studyPeopleRepository.findStudyPeopleByStuNumAndStuName(dto.stuNum!!, dto.stuName!!, it.id)
            }

}
