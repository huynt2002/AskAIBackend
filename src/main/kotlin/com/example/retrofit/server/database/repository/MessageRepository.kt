package com.example.retrofit.server.database.repository

import com.example.retrofit.server.database.model.Message
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository

interface MessageRepository : MongoRepository<Message, ObjectId> {
    fun findByConversationId(conversationId: ObjectId): List<Message>
}
