package com.project.meomi.domain.study.domain.repository

import com.project.meomi.domain.study.domain.QStudyPeople.studyPeople
import com.project.meomi.domain.study.presentation.data.dto.StudyPeopleQueryDto
import com.querydsl.core.types.Projections
import com.querydsl.jpa.impl.JPAQueryFactory

class StudyPeopleRepositoryImpl(
    private val queryFactory: JPAQueryFactory
): StudyPeopleRepositoryCustom {

    override fun findStudyPeopleByStuNum(stuNum: Int, id: Long): List<StudyPeopleQueryDto> =
        queryFactory.from(studyPeople)
            .select(
                Projections.constructor(
                    StudyPeopleQueryDto::class.java,
                    studyPeople.user.id,
                    studyPeople.user.stuNum,
                    studyPeople.user.name
                )
            )
            .where(studyPeople.user.stuNum.like("$stuNum%")
                .and(studyPeople.study.id.eq(id)))
            .orderBy(studyPeople.user.stuNum.asc())
            .fetch()

    override fun findStudyPeopleByStuName(stuName: String, id: Long): List<StudyPeopleQueryDto> =
        queryFactory.from(studyPeople)
            .select(
                Projections.constructor(
                    StudyPeopleQueryDto::class.java,
                    studyPeople.user.id,
                    studyPeople.user.stuNum,
                    studyPeople.user.name
                )
            )
            .where(studyPeople.user.name.like("%$stuName%")
                .and(studyPeople.study.id.eq(id)))
            .orderBy(studyPeople.user.stuNum.asc())
            .fetch()

    override fun findStudyPeopleByStuNumAndStuName(stuNum: Int, stuName: String, id: Long): List<StudyPeopleQueryDto> =
        queryFactory.from(studyPeople)
            .select(
                Projections.constructor(
                    StudyPeopleQueryDto::class.java,
                    studyPeople.user.id,
                    studyPeople.user.stuNum,
                    studyPeople.user.name
                )
            )
            .where(studyPeople.user.stuNum.like("$stuNum%")
                .and(studyPeople.study.id.eq(id))
                .and(studyPeople.user.name.like("%$stuName%")))
            .orderBy(studyPeople.user.stuNum.asc())
            .fetch()

}