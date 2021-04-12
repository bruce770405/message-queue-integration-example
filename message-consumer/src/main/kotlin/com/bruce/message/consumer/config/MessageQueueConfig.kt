package com.bruce.message.consumer.config

import org.springframework.amqp.core.Queue
import org.springframework.amqp.rabbit.connection.ConnectionFactory
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class MessageQueueConfig(private val connectionFactory: ConnectionFactory) {

    companion object {
        const val MY_TEST_CONSUMER_QUEUE_NAME = "my_test_queue"
    }

    @Bean
    fun queue(): Queue {
        return Queue(MY_TEST_CONSUMER_QUEUE_NAME)
    }


    @Bean
    fun rabbitTemplate(): RabbitTemplate? {
        val rabbitTemplate = RabbitTemplate(connectionFactory)
        rabbitTemplate.messageConverter = Jackson2JsonMessageConverter()
        return rabbitTemplate
    }
}