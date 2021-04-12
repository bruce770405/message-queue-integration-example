package com.bruce.message.consumer.mq

import com.bruce.message.consumer.config.MessageQueueConfig
import com.bruce.message.consumer.models.bo.MessagePushBo
import com.bruce.message.consumer.service.MessageService
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.springframework.amqp.rabbit.annotation.RabbitHandler
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.stereotype.Component

/**
 * singleton json mapper.
 */
object JsonMapper {
    val mapper = jacksonObjectMapper()
}

@Component
class MessageQueueConsumer constructor(private val messageService: MessageService) {

    companion object {
        val typeRef = object : TypeReference<MessageModel<MessagePushBo>>() {}
    }

    /**
     * handle a message.
     */
    @RabbitListener(queues = [MessageQueueConfig.MY_TEST_CONSUMER_QUEUE_NAME])
    @RabbitHandler
    fun process(message: String) {
        val messageModel: MessageModel<MessagePushBo> = JsonMapper.mapper.readValue(message, typeRef)
        messageService.doDandleMessage(messageModel.data)
    }

}