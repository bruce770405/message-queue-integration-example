package com.bruce.message.consumer.config

import org.springframework.amqp.core.Queue
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class MessageQueueConfig {

    companion object {
        const val MY_TEST_CONSUMER_QUEUE_NAME = "my_test_queue"
    }

    @Bean
    fun queue(): Queue {
        return Queue(MY_TEST_CONSUMER_QUEUE_NAME)
    }

    @Bean
    fun converter(): Jackson2JsonMessageConverter {
        return Jackson2JsonMessageConverter()
    }
}