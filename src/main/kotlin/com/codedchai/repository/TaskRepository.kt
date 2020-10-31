package com.codedchai.repository

import com.codedchai.domain.DailyTask
import com.codedchai.utils.MongoUtils
import com.mongodb.client.MongoCollection
import com.mongodb.client.model.Filters.eq
import mu.KotlinLogging
import javax.inject.Singleton

@Singleton
open class TaskRepository(mongoUtils: MongoUtils) {

  private val logger = KotlinLogging.logger { }

  var mongoCollection: MongoCollection<DailyTask> = mongoUtils.getMongoCollection("nihongo_tracker")


  open fun findItemsByUsername(username: String): List<DailyTask?> {
    val key = DailyTask::userName.name
    val filter = eq(key, username)

    return mongoCollection.find(filter).sortedBy { it.dueDate }
  }

}