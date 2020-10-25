package com.codedchai.controller

import com.codedchai.domain.DailyTask
import com.codedchai.domain.Reminder
import com.codedchai.service.GenkiReminderService
import io.micronaut.context.annotation.Parameter
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Header
import io.micronaut.http.annotation.Produces
import java.util.*

@Controller("/v1/genki")
class GenkiController(
        val genkiReminderService: GenkiReminderService
) {

    @Get("/reminders")
    @Produces(MediaType.APPLICATION_JSON)
    fun getReminders(): List<Reminder> {
        return genkiReminderService.getReminders()
    }

    @Get("/tasks")
    @Produces(MediaType.APPLICATION_JSON)
    fun getTasks(
            @Header("x-start-date") startDate: Date,
            @Header("x-end-date") endDate: Date,
    ): List<DailyTask> {
        return genkiReminderService.getTasks(startDate, endDate)
    }


}