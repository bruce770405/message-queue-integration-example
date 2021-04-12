package com.bruce.message.producer.service.impl

import com.bruce.message.producer.models.bo.MessagePushBo
import com.bruce.message.producer.mq.MessageModel
import com.bruce.message.producer.mq.MessageQueueProducer
import com.bruce.message.producer.service.MessageService
import org.springframework.stereotype.Service


@Service
class MessageServiceImpl(val mqProducer: MessageQueueProducer) : MessageService {

    override fun pushAndHandleMessage(messagePushBo: MessagePushBo) {
        val message = MessageModel("message-" + messagePushBo.id, messagePushBo)
        mqProducer.sendMessage(message)
    }
}