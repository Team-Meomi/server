package com.project.meomi.infrastructure.discord.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties(value = "discord")
data class DiscordProperties(
    val webhookURL: String
)