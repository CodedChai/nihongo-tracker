package com.codedchai.utils

import com.codedchai.domain.DailyTask
import com.mongodb.ReadConcern
import com.mongodb.WriteConcern
import com.mongodb.client.MongoCollection
import com.mongodb.client.MongoDatabase
import mu.KotlinLogging
import javax.inject.Singleton

@Singleton
class MongoUtils(private val mongoDatabase: MongoDatabase) {

  private val logger = KotlinLogging.logger { }

  fun getMongoCollection(collectionName: String): MongoCollection<DailyTask> {

    logger.info { "getting mongo collection $collectionName" }

    return mongoDatabase
        .withWriteConcern(WriteConcern.ACKNOWLEDGED)
        .withReadConcern(ReadConcern.DEFAULT)
        .getCollection(collectionName, DailyTask::class.java)
        .withWriteConcern(WriteConcern.ACKNOWLEDGED)
        .withReadConcern(ReadConcern.DEFAULT)
  }
}