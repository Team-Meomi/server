package com.project.meomi.domain.study.domain.repository

import com.project.meomi.domain.study.domain.Study
import org.springframework.data.repository.CrudRepository

interface StudyRepository: CrudRepository<Study, Long> {
}