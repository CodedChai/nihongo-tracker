package com.codedchai.controller

import com.codedchai.domain.DailyTask
import com.codedchai.domain.Reminder
import com.codedchai.service.GenkiReminderService
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.*

@Controller("/v1")
open class GenkiController(
  val genkiReminderService: GenkiReminderService
) {

  @Get("/reminders")
  @Produces(MediaType.APPLICATION_JSON)
  fun getReminders(): List<Reminder> {
    return genkiReminderService.getReminders()
  }

  @Get("/tasks")
  @Produces(MediaType.APPLICATION_JSON)
  suspend fun getTasks(
    @Header("x-user-name") userName: String
  ): List<DailyTask?> {
    return genkiReminderService.getTasks(userName)
  }

  @Post("/tasks/create")
  suspend fun createDailyTask(
    @Header("x-user-name") userName: String,
  ) {
    genkiReminderService.saveDailyTask()
  }

}