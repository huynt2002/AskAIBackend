package com.example.retrofit.server.controller

import com.example.retrofit.server.database.converter.toConversationResponse
import com.example.retrofit.server.database.model.Conversation
import com.example.retrofit.server.database.repository.ConversationRepository
import com.example.retrofit.server.use_case.UseCase
import java.time.Instant
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/conversations")
class ConversationController(
    private val useCase: UseCase,
    private val conversationRepository: ConversationRepository,
) {
    data class ConversationRequest(val id: String?, val title: String)

    data class ConversationResponse(
        val id: String,
        val title: String,
        val createAt: Instant,
        val updateAt: Instant,
    )

    @GetMapping
    fun getConversations(): List<ConversationResponse> {
        val userId = useCase.getUserId()
        return conversationRepository.findByUserId(userId).map { it.toConversationResponse() }
    }

    @PostMapping
    fun save(@RequestBody body: ConversationRequest): ConversationResponse {
        val userId = useCase.getUserId()
        val conversation =
            conversationRepository.save(
                Conversation(
                    title = body.title,
                    userId = userId,
                    createAt = Instant.now(),
                    updateAt = Instant.now(),
                )
            )
        return conversation.toConversationResponse()
    }

    @PostMapping(path = ["/{id}"])
    fun update(
        @PathVariable id: String,
        @RequestBody body: ConversationRequest,
    ): ConversationResponse {
        val oldConversation = useCase.getConversationFromId(id)
        val conversation =
            conversationRepository.save(
                Conversation(
                    title = body.title,
                    userId = oldConversation.userId,
                    createAt = oldConversation.createAt,
                    updateAt = Instant.now(),
                    id = oldConversation.id,
                )
            )
        return conversation.toConversationResponse()
    }
}
