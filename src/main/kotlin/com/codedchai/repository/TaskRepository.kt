package com.codedchai.repository

import com.codedchai.domain.DailyTask
import com.mongodb.client.model.Filters.eq
import kotlinx.coroutines.runBlocking
import mu.KotlinLogging
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.reactivestreams.KMongo
import javax.inject.Singleton

@Singleton
class TaskRepository() {

  private val logger = KotlinLogging.logger { }

  private val client =
    KMongo.createClient("mongodb+srv://nihongo:<pw>>@cluster0.cb0gm.mongodb.net/NihongoTracker?retryWrites=true&w=majority").coroutine
  private val database = client.getDatabase("NihongoTracker") //normal java driver usage
  private val collection = database.getCollection<DailyTask>("DailyTask") //KMongo extension method

  suspend fun findItemsByUsername(username: String): List<DailyTask?> {
    logger.info { "searching for username $username" }

    return runBlocking {
      collection.find(
        eq(DailyTask::userName.name, username)
      ).toList()
    }
  }

  suspend fun save(dailyTask: DailyTask) {
    logger.info { "saving $dailyTask" }

    runBlocking {
      collection.save(dailyTask)
    }
  }
}
