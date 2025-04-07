package com.example.retrofit.server.database.converter

import com.example.retrofit.server.controller.MessageController
import com.example.retrofit.server.database.model.Message

fun Message.toMessageResponse(): MessageController.MessageResponse {
    return MessageController.MessageResponse(
        content = content,
        createAt = createAt,
        imagePath = imagePath,
    )
}
