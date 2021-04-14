package com.bruce.message.consumer.mq

import com.bruce.message.consumer.config.MessageQueueConfig
import com.bruce.message.consumer.models.bo.MessagePushBo
import com.bruce.message.consumer.service.MessageService
import org.springframework.amqp.rabbit.annotation.RabbitHandler
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.stereotype.Component

@Component
class MessageQueueConsumer constructor(private val messageService: MessageService) {


    /**
     * handle a message.
     */
    @RabbitListener(queues = [MessageQueueConfig.MY_TEST_CONSUMER_QUEUE_NAME])
    @RabbitHandler
    fun process(@Payload message: MessageModel<MessagePushBo>) {
        message.data?.let { messageService.doDandleMessage(it) }
    }

}