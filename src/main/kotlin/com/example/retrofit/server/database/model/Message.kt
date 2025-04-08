package com.example.retrofit.server.database.model

import java.time.Instant
import org.bson.types.ObjectId
import org.springframework.data.mongodb.core.mapping.Document

@Document("messages")
data class Message(
    val content: String,
    val imagePath: String,
    val role:String,
    val createAt: Instant,
    val conversationId: ObjectId,
)
