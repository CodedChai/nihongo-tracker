package com.codedchai.service

import com.codedchai.domain.Reminder
import mu.KotlinLogging
import javax.inject.Singleton

@Singleton
class GenkiReminderService() {

    private val logger = KotlinLogging.logger {}

    fun getReminders(): List<Reminder> {
        logger.debug { "getting genki reminders" }
        return emptyList()
    }

}