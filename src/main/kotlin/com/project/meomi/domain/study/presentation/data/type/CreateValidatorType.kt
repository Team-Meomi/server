package com.project.meomi.domain.study.presentation.data.type

import java.util.*

enum class CreateValidatorType(val value: String) {
    CONFERENCE("컨퍼런스"),
    STUDY("스터디");

    companion object {
        fun findName(value: String): CreateValidatorType {
            return Arrays.stream(CreateValidatorType.values())
                .filter { it.value == value }
                .findFirst()
                .orElseThrow()
        }
    }
}