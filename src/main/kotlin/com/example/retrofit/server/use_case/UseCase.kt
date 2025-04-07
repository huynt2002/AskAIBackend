package com.example.retrofit.server.use_case

import com.example.retrofit.server.database.model.Conversation
import com.example.retrofit.server.database.repository.ConversationRepository
import com.example.retrofit.server.database.repository.UserRepository
import com.example.retrofit.server.util.getSecurityAuthUid
import org.bson.types.ObjectId
import org.springframework.context.annotation.Configuration

@Configuration
class UseCase(
    private val userRepository: UserRepository,
    private val conversationRepository: ConversationRepository,
) {
    fun getUserId(): ObjectId {
        val uid = getSecurityAuthUid()
        val user = userRepository.findByUid(uid)
        user?.let {
            return it.id
        } ?: throw IllegalArgumentException("User not found.")
    }

    fun getConversationFromId(id: String): Conversation {
        val userId = getUserId()
        val conversation =
            conversationRepository.findById(ObjectId(id)).orElseThrow {
                IllegalArgumentException("Conversation not found.")
            }
        if (userId == conversation.userId) {
            return conversation
        }
        throw IllegalArgumentException("Conversation not found.")
    }
}
