package com.bruce.message.consumer.controller

import com.bruce.message.consumer.models.bo.MessagePushLogBo
import com.bruce.message.consumer.models.dto.MessagePushLogResponse
import com.bruce.message.consumer.models.dto.PageAndSortRequest
import com.bruce.message.consumer.service.MessageService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value = ["/message"])
class MessageController(val messageService: MessageService) {

    /**
     * get message push handle finish logs
     */
    @GetMapping(value = ["/log"])
    fun messagePushLog(@RequestParam(required = true) start: Long,
                       @RequestParam(required = true) end: Long,
                       @RequestParam(required = true) page: PageAndSortRequest): ResponseEntity<MessagePushLogResponse<MessagePushLogBo>> {
        val result: MessagePushLogResponse<MessagePushLogBo> = messageService.getMessagePushHandleLog(start, end, page.convertToPageable())
        return ResponseEntity.ok(result)
    }
}