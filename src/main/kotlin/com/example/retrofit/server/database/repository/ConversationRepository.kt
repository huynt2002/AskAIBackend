package com.example.retrofit.server.database.repository

import com.example.retrofit.server.database.model.Conversation
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository

interface ConversationRepository : MongoRepository<Conversation, ObjectId> {
    fun findByUserId(userId: ObjectId): List<Conversation>
}
