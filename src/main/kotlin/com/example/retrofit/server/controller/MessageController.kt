package com.example.retrofit.server.controller

import com.example.retrofit.server.database.converter.toMessageResponse
import com.example.retrofit.server.database.model.Message
import com.example.retrofit.server.database.repository.MessageRepository
import com.example.retrofit.server.use_case.UseCase
import java.time.Instant
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("messages")
class MessageController(
    private val useCase: UseCase,
    private val messageRepository: MessageRepository,
) {
    data class MessageResponse(val content: String, val imagePath: String, val role:String,val createAt: Instant)

    data class MessageRequest(val content: String, val imagePath: String?,val role:String,)

    @GetMapping
    fun getMessages(@RequestParam conversationId: String): List<MessageResponse> {
        val conversation = useCase.getConversationFromId(conversationId)
        return messageRepository.findByConversationId(conversationId = conversation.id).map {
            it.toMessageResponse()
        }
    }

    @PostMapping
    fun save(
        @RequestParam conversationId: String,
        @RequestBody body: MessageRequest,
    ): MessageResponse {
        val conversation = useCase.getConversationFromId(conversationId)
        return messageRepository
            .save(
                Message(
                    content = body.content,
                    imagePath = body.imagePath ?: "",
                    createAt = Instant.now(),
                    conversationId = conversation.id,
                    role = body.role
                )
            )
            .toMessageResponse()
    }
}
