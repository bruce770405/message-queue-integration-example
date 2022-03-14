package com.bruce.message.producer.mq

import com.bruce.message.producer.config.Json
import com.bruce.message.producer.exception.MessageHandleException
import com.bruce.message.producer.persist.entity.RetryMessageEntity
import com.bruce.message.producer.persist.repository.MessageConfirmRepository
import com.bruce.message.producer.persist.repository.RetryMessageRepository
import org.slf4j.LoggerFactory
import org.springframework.amqp.rabbit.connection.CorrelationData
import org.springframework.amqp.rabbit.core.RabbitTemplate


class CustomerConfirmCallBack(
    private val retryMessageRepository: RetryMessageRepository,
    private val messageConfirmRepository: MessageConfirmRepository
) : RabbitTemplate.ConfirmCallback {

    private val logger = LoggerFactory.getLogger(javaClass)

    override fun confirm(correlationData: CorrelationData?, ack: Boolean, cause: String?) {
        if (!ack) {

            logger.error("fail! ,,, cause :{}, id :{}", cause, correlationData?.id)
            if (correlationData != null) {
                val id = correlationData.id;
                val optional = messageConfirmRepository.findById(id)
                optional.ifPresentOrElse({ messageConfirm ->
                    val entity = RetryMessageEntity(id, Json.mapper.writeValueAsString(messageConfirm.data), false)
                    retryMessageRepository.save(entity)
                }, {
                    logger.error("messageConfirm data miss, id :$id")
                    throw MessageHandleException("messageConfirm data miss, id :$id", null)
                })
            }

        } else {
            logger.debug("success ,,, id :{}", correlationData?.id)
        }
    }
}
