package com.bruce.message.producer.mq

import com.bruce.message.producer.config.MessageQueueConfig
import com.bruce.message.producer.models.bo.MessagePushBo
import org.slf4j.LoggerFactory
import org.springframework.amqp.core.MessageDeliveryMode
import org.springframework.amqp.core.MessagePostProcessor
import org.springframework.amqp.rabbit.connection.CorrelationData
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.stereotype.Component

@Component
class MessageQueueProducer constructor(private val rabbit: RabbitTemplate) {

    private val logger = LoggerFactory.getLogger(javaClass)

    init {
        rabbit.setConfirmCallback { data, ack, cause ->
            logger.debug("RabbitMQ data confirm")
            if (!ack) {
                logger.error("fail!" + cause + data.toString())
            } else {
                logger.info("success,ID：" + data?.id)
            }
        }
    }

    /**
     * send a message to rabbitMQ.
     * @param message push data.
     */
    fun sendMessage(message: MessageModel<MessagePushBo>) {
        val correlationData = CorrelationData(message.id)
        rabbit.convertAndSend(MessageQueueConfig.MY_TEST_PRODUCER_QUEUE_NAME, message, messagePostProcessor(), correlationData)
    }

    private fun messagePostProcessor(): MessagePostProcessor {
        return MessagePostProcessor(({ message ->
            message.messageProperties.deliveryMode = MessageDeliveryMode.NON_PERSISTENT
            message
        }))
    }
}