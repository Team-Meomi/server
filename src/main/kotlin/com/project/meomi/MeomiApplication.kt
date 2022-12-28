package com.project.meomi

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling
import java.util.*
import javax.annotation.PostConstruct

@EnableScheduling
@ConfigurationPropertiesScan
@SpringBootApplication
class MeomiApplication

@PostConstruct
fun timeZone() {
	TimeZone.setDefault(TimeZone.getTimeZone("Asia/Seoul"))
}
fun main(args: Array<String>) {
	runApplication<MeomiApplication>(*args)
}
