package com.project.meomi.infrastructure.discord.service.impl

import com.project.meomi.domain.study.domain.repository.StudyRepository
import com.project.meomi.global.annotation.TransactionWithReadOnly
import com.project.meomi.infrastructure.discord.config.DiscordProperties
import com.project.meomi.infrastructure.discord.service.DiscordService
import org.json.simple.JSONObject
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import java.time.LocalDate
import java.time.format.DateTimeFormatter


@Service
class DiscordServiceImpl(
    private val studyRepository: StudyRepository,
    private val discordProperties: DiscordProperties
): DiscordService {

    @TransactionWithReadOnly
    override fun reminderMorningTime() {
        val jsonObject = JSONObject()
        val study = studyRepository.findStudyByStudyTypeAndDate("컨퍼런스", LocalDate.now())

        jsonObject["content"] = "\uD83D\uDCE2" + study.date.format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일")) + "\uD83D\uDCE2 \n [ " + study.title + " ] 컨퍼런스가 오늘 10, 11교시에 예정되어 있습니다."
        sendDiscord(jsonObject)
    }

    @TransactionWithReadOnly
    override fun reminderTenMinuteAgo() {
        val jsonObject = JSONObject()
        val study = studyRepository.findStudyByStudyTypeAndDate("컨퍼런스", LocalDate.now())

        jsonObject["content"] = "\uD83D\uDCE2" + study.date.format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일")) + "\uD83D\uDCE2 \n [ " + study.title + " ] 컨퍼런스가 한시간 뒤에 예정되어 있습니다."
        sendDiscord(jsonObject)
    }

    private fun sendDiscord(jsonObject: JSONObject) {
        val headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_JSON
        val restTemplate = RestTemplate()
        val entity: HttpEntity<String> = HttpEntity(jsonObject.toString(), headers)
        restTemplate.postForObject(discordProperties.webhookURL, entity, String::class.java)
    }

}