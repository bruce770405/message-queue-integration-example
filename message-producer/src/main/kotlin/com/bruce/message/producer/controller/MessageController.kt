package com.bruce.message.producer.controller

import com.bruce.message.producer.models.bo.MessagePushBo
import com.bruce.message.producer.models.dto.MessagePushRequest
import com.bruce.message.producer.service.MessageService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping(value = ["/message"])
class MessageController(val messageService: MessageService) {

    @PostMapping(value = ["/push"])
    fun pushSomeMessage(@RequestBody rq: MessagePushRequest): ResponseEntity<Void> {
        val messageId: String = UUID.randomUUID().toString()
        messageService.pushAndHandleMessage(MessagePushBo(messageId, rq.title, rq.content))
        return ResponseEntity.noContent().build()
    }
}