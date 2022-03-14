package com.bruce.message.consumer.mq

import com.bruce.message.consumer.config.MessageQueueConfig
import com.bruce.message.consumer.models.bo.MessagePushBo
import com.bruce.message.consumer.service.MessageService
import com.rabbitmq.client.Channel
import org.springframework.amqp.rabbit.annotation.RabbitHandler
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.amqp.support.AmqpHeaders
import org.springframework.messaging.handler.annotation.Header
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.stereotype.Component
import javax.persistence.PersistenceException

@Component
class MessageQueueConsumer constructor(private val messageService: MessageService) {


    /**
     * handle a message.
     */
    @RabbitListener(queues = [MessageQueueConfig.MY_TEST_CONSUMER_QUEUE_NAME])
    @RabbitHandler
    fun process(@Payload message: MessageModel<MessagePushBo>, channel: Channel, @Header(AmqpHeaders.DELIVERY_TAG) tag: Long) {
        try {
            messageService.doHandleMessage(message.data!!)
            channel.basicAck(tag, false);
        } catch (e: PersistenceException) {
            // reject but the data rollback to queue
            channel.basicNack(tag, false, true)
        } catch (e: Exception) {
            // reject but the data will delete from queue
            channel.basicNack(tag, false, true)
        }
    }

}
