package com.codedchai.service

import com.codedchai.domain.DailyTask
import com.codedchai.domain.Reminder
import com.codedchai.repository.TaskRepository
import mu.KotlinLogging
import java.util.*
import javax.inject.Singleton

@Singleton
open class GenkiReminderService(private val taskRepository: TaskRepository) {

  private val logger = KotlinLogging.logger {}

  fun getReminders(): List<Reminder> {
    logger.debug { "getting genki reminders" }

    return listOf(Reminder(createdTimestamp = Date(), chapterNumber = 1, reminderDate = Date()))
  }

  suspend fun getTasks(userName: String): List<DailyTask?> {
    return taskRepository.findItemsByUsername(userName)
      .also { logger.info { "found $it from the repo" } }
  }

  suspend fun saveDailyTask() {
    val dailyTask = DailyTask(null, 1, 1, Date(), false, "Connor")
    taskRepository.save(dailyTask = dailyTask)
  }

}