package com.codedchai.domain

import com.fasterxml.jackson.annotation.JsonFormat
import java.util.*

data class DailyTask(
    val _id: String?,
    val pageNumber: Int,
    val chapterNumber: Int,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm a")
    val dueDate: Date,
    val isComplete: Boolean,
    val userName: String // Connor or Shae
)