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
    override fun searchAudiovisualPeople(stuNum: Int?, stuName: String?): List<StudyPeopleQueryDto> {
        val conference = studyRepository.findStudyByDateAndStudyType(LocalDate.now(), "컨퍼런스")

        // 학번을 안보냈을 경우
        stuNum ?: return studyPeopleRepository.findStudyPeopleByStuName(stuName!!, conference[0].id)

        // 이름을 안보냈을 경우
        stuName ?: return studyPeopleRepository.findStudyPeopleByStuNum(stuNum, conference[0].id)

        // 둘다 보냈을 경우
        return studyPeopleRepository.findStudyPeopleByStuNumAndStuName(stuNum, stuName, conference[0].id)
    }

    @TransactionWithReadOnly
    override fun searchHomebasePeople(stuNum: Int?, stuName: String?): List<StudyPeopleQueryDto> {
        studyRepository.findStudyByDateAndStudyType(LocalDate.now(), "스터디")
            .map {
                // 학번을 안보냈을 경우
                stuNum ?: return studyPeopleRepository.findStudyPeopleByStuName(stuName!!, it.id)

                // 이름을 안보냈을 경우
                stuName ?: return studyPeopleRepository.findStudyPeopleByStuNum(stuNum, it.id)

                // 둘다 보냈을 경우
                return studyPeopleRepository.findStudyPeopleByStuNumAndStuName(stuNum, stuName, it.id)
            }

        return emptyList()
    }

}