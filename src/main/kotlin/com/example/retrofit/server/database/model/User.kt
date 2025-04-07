package com.example.retrofit.server.database.model

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document("users") data class User(val uid: String, @Id val id: ObjectId = ObjectId.get())
