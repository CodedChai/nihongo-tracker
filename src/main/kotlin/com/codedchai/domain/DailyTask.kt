package com.codedchai.domain

import java.util.*

data class DailyTask (
    val pageNumber: Int,
    val chapterNumber : Int,
    val dailyTaskDate: Date,
    val isComplete: Boolean
)