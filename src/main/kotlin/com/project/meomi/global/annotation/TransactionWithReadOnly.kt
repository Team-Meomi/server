package com.project.meomi.global.annotation

import org.springframework.transaction.annotation.Transactional

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@Transactional(readOnly = true, rollbackFor = [Exception::class])
annotation class TransactionWithReadOnly
