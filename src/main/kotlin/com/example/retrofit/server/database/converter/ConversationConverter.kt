package com.example.retrofit.server.database.converter

import com.example.retrofit.server.controller.ConversationController
import com.example.retrofit.server.database.model.Conversation

fun Conversation.toConversationResponse(): ConversationController.ConversationResponse {
    return ConversationController.ConversationResponse(
        title = title,
        id = id.toHexString(),
        createAt = createAt,
        updateAt = updateAt,
    )
}
