package com.project.meomi.domain.study.schedule

import com.project.meomi.infrastructure.discord.service.DiscordService
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
class StudySchedule(
    private val discordService: DiscordService
) {

    @Scheduled(cron = "0 0 8 ? * 1-4") // 월 ~ 목 오전 8시에 디스코드 알림을 보내준다
    fun morningTimeScheduler() {
        discordService.reminderMorningTime()
    }

    @Scheduled(cron = "0 20 19 ? * 1-4") // 월 ~ 목 오후 7시 20분에 디스코드 알림을 보내준다
    fun tenMinuteAgoScheduler() {
        discordService.reminderTenMinuteAgo()
    }

}