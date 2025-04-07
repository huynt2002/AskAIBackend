package com.example.retrofit.server.database.model

import java.time.Instant
import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document("conversations")
data class Conversation(
    val title: String,
    val createAt: Instant,
    val updateAt: Instant,
    val userId: ObjectId,
    @Id val id: ObjectId = ObjectId.get(),
)
