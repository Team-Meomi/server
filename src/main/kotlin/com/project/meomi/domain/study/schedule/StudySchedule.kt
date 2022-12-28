package com.project.meomi.domain.study.schedule

import com.project.meomi.infrastructure.discord.service.DiscordService
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
class StudySchedule(
    private val discordService: DiscordService
) {

    @Scheduled(cron = "0 0 8 * * 2-5", zone = "Asia/Seoul") // 월 ~ 목 오전 8시에 디스코드 알림을 보내준다
    fun morningTimeScheduler() {
        println("8시 알림 발송")
        discordService.reminderMorningTime()
    }

    @Scheduled(cron = "0 20 19 ? * 2-5", zone = "Asia/Seoul") // 월 ~ 목 오후 7시 20분에 디스코드 알림을 보내준다
    fun tenMinuteAgoScheduler() {
        println("7시 20분 알림 발송")
        discordService.reminderTenMinuteAgo()
    }

}