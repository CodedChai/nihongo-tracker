package com.codedchai.service

import com.codedchai.domain.DailyTask
import com.codedchai.domain.Reminder
import com.codedchai.repository.TaskRepository
import mu.KotlinLogging
import java.util.*
import javax.inject.Singleton

@Singleton
open class GenkiReminderService(
  private val taskRepository: TaskRepository
) {

  private val logger = KotlinLogging.logger {}

  fun getReminders(): List<Reminder> {
    logger.debug { "getting genki reminders" }

    return listOf(Reminder(createdTimestamp = Date(), chapterNumber = 1, reminderDate = Date()))
  }

  suspend fun getTasks(userName: String): List<DailyTask?> {
    return taskRepository.findItemsByUsername(userName)
      .also { logger.info { "found $it from the repo" } }
  }

  suspend fun saveDailyTask(dailyTask: DailyTask, userName: String): DailyTask {
    logger.info { "saving daily task for $userName" }

    taskRepository.save(dailyTask = dailyTask)

    return dailyTask
  }

  suspend fun completeDailyTask(dailyTask: DailyTask, dailyTaskId: String, userName: String): DailyTask {
    if (dailyTask.isComplete) {
      return dailyTask
    }

    logger.info { "saving daily task for ${dailyTask.userName}" }

    val wasAcknowledged = taskRepository.updateTaskToCompleted(dailyTaskId = dailyTaskId, userName = userName)

    return if (wasAcknowledged) {
      dailyTask.copy(isComplete = true)
    } else {
      dailyTask
    }
  }

}