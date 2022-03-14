package com.bruce.message.producer.mq

import com.bruce.message.producer.config.Json
import com.bruce.message.producer.persist.repository.RetryMessageRepository
import org.springframework.amqp.core.ReturnedMessage
import org.springframework.amqp.rabbit.core.RabbitTemplate
import java.io.ByteArrayInputStream
import java.io.ObjectInputStream

class CustomerReturnsCallback<T>(private val retryMessageRepository: RetryMessageRepository) :
    RabbitTemplate.ReturnsCallback {


    override fun returnedMessage(returned: ReturnedMessage) {

        println("message : " + returned.message);
        println("message : " + returned.replyCode);
        println("replyText ：" + returned.replyText);
        println("exchange : " + returned.exchange);
        println("routing : " + returned.routingKey);
//        byteToObject(returned.message)
    }

    private fun <T> byteToObject(bytes: ByteArray, clazz: Class<T>): T {
        try {
            ByteArrayInputStream(bytes).use { bis ->
                ObjectInputStream(bis).use { ois ->
                    val json: String = ois.readUTF()
                    return Json.mapper.readValue(json, clazz)
                }
            }
        } catch (e: Exception) {
            throw Exception()
        }
    }
}