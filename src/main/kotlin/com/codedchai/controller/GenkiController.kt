package com.codedchai.controller

import com.codedchai.domain.Reminder
import com.codedchai.service.GenkiReminderService
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Produces

@Controller("/v1/genki")
class GenkiController(
        val genkiReminderService: GenkiReminderService
) {

    @Get("/reminders")
    @Produces(MediaType.APPLICATION_JSON)
    fun getReminders(): List<Reminder> {
        return genkiReminderService.getReminders()
    }
}