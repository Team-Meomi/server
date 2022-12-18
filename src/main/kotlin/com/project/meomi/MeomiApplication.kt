package com.project.meomi

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@ConfigurationPropertiesScan
@SpringBootApplication
class MeomiApplication

fun main(args: Array<String>) {
	runApplication<MeomiApplication>(*args)
}
