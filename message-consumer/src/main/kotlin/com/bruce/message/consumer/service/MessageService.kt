package com.bruce.message.consumer.service

import com.bruce.message.consumer.models.bo.MessagePushBo
import com.bruce.message.consumer.models.bo.MessagePushLogBo
import com.bruce.message.consumer.models.dto.MessagePushLogResponse
import org.springframework.data.domain.Pageable

interface MessageService {

    fun doHandleMessage(messagePushBo: MessagePushBo)

    fun getMessagePushHandleLog(startTime: Long, endTime: Long, pageable: Pageable): MessagePushLogResponse<MessagePushLogBo>

}