package com.codedchai.domain

import java.util.*

data class Reminder(
    val createdTimestamp: Date,
    val chapterNumber: Int,
    val reminderDate: Date
)