package com.codedchai.repository

import com.codedchai.domain.DailyTask
import com.mongodb.client.model.Filters.eq
import mu.KotlinLogging
import org.litote.kmongo.and
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.reactivestreams.KMongo
import org.litote.kmongo.setValue
import javax.inject.Singleton

@Singleton
class TaskRepository() {

  private val logger = KotlinLogging.logger { }

  private val client =
    KMongo.createClient("mongodb+srv://nihongo:<pw>@cluster0.cb0gm.mongodb.net/NihongoTracker?retryWrites=true&w=majority").coroutine
  private val database = client.getDatabase("NihongoTracker")
  private val collection = database.getCollection<DailyTask>("DailyTask")

  suspend fun findItemsByUsername(username: String): List<DailyTask?> {
    logger.info { "searching for username $username" }

    return collection.find(
      eq(DailyTask::userName.name, username)
    ).toList()
  }

  suspend fun save(dailyTask: DailyTask): Boolean {
    logger.info { "saving $dailyTask" }

    return collection.save(dailyTask)?.wasAcknowledged() ?: false
  }

  suspend fun updateTaskToCompleted(dailyTaskId: String): Boolean {
    logger.info { "completing task for $dailyTaskId" }

    return collection.updateOne(
      eq(DailyTask::_id.name, dailyTaskId),
      and(
        setValue(DailyTask::isComplete, true)
      )
    ).wasAcknowledged()
  }
}
