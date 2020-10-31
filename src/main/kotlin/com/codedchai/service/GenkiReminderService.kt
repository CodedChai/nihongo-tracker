package com.codedchai.service

import com.codedchai.domain.DailyTask
import com.codedchai.domain.Reminder
import com.codedchai.repository.TaskRepository
import mu.KotlinLogging
import java.util.*
import javax.inject.Singleton

@Singleton
class GenkiReminderService(private val taskRepository: TaskRepository) {

  private val logger = KotlinLogging.logger {}

  fun getReminders(): List<Reminder> {
    logger.debug { "getting genki reminders" }
    return emptyList()
  }

  fun getTasks(userName: String, startDate: Date, endDate: Date): List<DailyTask> {
    // TODO: Query Mongo for all tasks between the given date range and user
    logger.info { taskRepository.findItemsByUsername(userName) }
    return emptyList()
  }

}