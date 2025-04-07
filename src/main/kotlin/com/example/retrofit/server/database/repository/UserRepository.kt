package com.example.retrofit.server.database.repository

import com.example.retrofit.server.database.model.User
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository

interface UserRepository : MongoRepository<User, ObjectId> {
    fun findByUid(uid: String): User?
}
