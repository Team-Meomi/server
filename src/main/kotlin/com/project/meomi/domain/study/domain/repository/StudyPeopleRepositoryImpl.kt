//package com.project.meomi.domain.study.domain.repository
//
//import com.project.meomi.domain.study.domain.QStudy.study
//import com.project.meomi.domain.study.domain.QStudyPeople.studyPeople
//import com.project.meomi.domain.study.domain.StudyPeople
//import com.project.meomi.domain.study.presentation.data.dto.StudyPeopleQueryDto
//import com.querydsl.core.types.Projections
//import com.querydsl.jpa.impl.JPAQueryFactory
//
//class StudyPeopleRepositoryImpl(
//    private val queryFactory: JPAQueryFactory
//): StudyPeopleRepositoryCustom {
//
//    override fun findStudyPeopleByStuNum(stuNum: Int): List<StudyPeople> {
//        return queryFactory.from(studyPeople)
//            .select(
//                Projections.constructor(
//                    StudyPeopleQueryDto::class.java,
//                    studyPeople.user.id,
//                    studyPeople.user.stuNum,
//                    studyPeople.user.name
//                )
//            )
//            .where(studyPeople.user.stuNum.like(stuNum + "%"))
//            .innerJoin(study, studyPeople)
//            .orderBy(studyPeople.user.stuNum.asc())
//    }
//
//}