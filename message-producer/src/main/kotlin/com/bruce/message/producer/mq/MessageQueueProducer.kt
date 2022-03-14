package com.bruce.message.producer.mq

import com.bruce.message.producer.config.MessageQueueConfig
import com.bruce.message.producer.models.bo.MessagePushBo
import com.bruce.message.producer.persist.entity.ConfirmMessage
import com.bruce.message.producer.persist.repository.MessageConfirmRepository
import com.bruce.message.producer.persist.repository.RetryMessageRepository
import org.slf4j.LoggerFactory
import org.springframework.amqp.core.MessageDeliveryMode
import org.springframework.amqp.core.MessagePostProcessor
import org.springframework.amqp.core.ReturnedMessage
import org.springframework.amqp.rabbit.connection.CorrelationData
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.stereotype.Component

@Component
class MessageQueueProducer constructor(
    private val rabbit: RabbitTemplate,
    private val retryMessageRepository: RetryMessageRepository,
    private val messageConfirmRepository: MessageConfirmRepository
) {

    init {
        rabbit.setConfirmCallback(CustomerConfirmCallBack(retryMessageRepository, messageConfirmRepository))
        rabbit.setReturnsCallback(CustomerReturnsCallback<MessageModel<MessagePushBo>>(retryMessageRepository))
    }

    /**
     * send a message to rabbitMQ.
     * @param message push data.
     */
    fun sendMessage(message: MessageModel<MessagePushBo>) {
        val correlationData = CorrelationData(message.id.orEmpty())

        // cache data
        val messageConfirm = ConfirmMessage(message.id, message.data)
        messageConfirmRepository.save(messageConfirm)

        rabbit.convertAndSend(
            MessageQueueConfig.MY_TEST_PRODUCER_EXCHANGE_NAME,
            MessageQueueConfig.MY_TEST_PRODUCER_ROUTING_KEY,
            message,
            messagePostProcessor(),
            correlationData
        )
    }

    private fun messagePostProcessor(): MessagePostProcessor {
        return MessagePostProcessor(({ message ->
            message.messageProperties.deliveryMode = MessageDeliveryMode.PERSISTENT
            message
        }))
    }
}