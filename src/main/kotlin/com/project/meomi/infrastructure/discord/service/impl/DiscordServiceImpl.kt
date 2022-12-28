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
        studyRepository.findStudyByDateAndStudyType(LocalDate.now(), "컨퍼런스")
            .map {
                jsonObject["content"] = "\uD83D\uDCE2" + it.date.format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일")) + "\uD83D\uDCE2 \n [ " + it.title + " ] 컨퍼런스가 오늘 10, 11교시에 예정되어 있습니다."
                sendDiscord(jsonObject)
            }
    }

    @TransactionWithReadOnly
    override fun reminderTenMinuteAgo() {
        val jsonObject = JSONObject()
        studyRepository.findStudyByDateAndStudyType(LocalDate.now(), "컨퍼런스")
            .map {
                jsonObject["content"] = "\uD83D\uDCE2" + it.date.format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일")) + "\uD83D\uDCE2 \n [ " + it.title + " ] 컨퍼런스가 10분 뒤에 예정되어 있습니다. \n 슬슬 시청각실로 이동해주세요!"
                sendDiscord(jsonObject)
            }
    }

    private fun sendDiscord(jsonObject: JSONObject) {
        val headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_JSON
        val restTemplate = RestTemplate()
        val entity: HttpEntity<String> = HttpEntity(jsonObject.toString(), headers)
        restTemplate.postForObject(discordProperties.webhookURL, entity, String::class.java)
    }


}