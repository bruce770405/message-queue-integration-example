package com.bruce.message.producer.config

import org.springframework.amqp.core.Binding
import org.springframework.amqp.core.Queue
import org.springframework.amqp.rabbit.connection.ConnectionFactory
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.amqp.core.BindingBuilder

import org.springframework.amqp.core.TopicExchange


@Configuration
class MessageQueueConfig(private val connectionFactory: ConnectionFactory) {

    companion object {
        const val MY_TEST_PRODUCER_QUEUE_NAME = "my_test_queue"
        const val MY_TEST_PRODUCER_EXCHANGE_NAME = "my_test_exchange"
        const val MY_TEST_PRODUCER_ROUTING_KEY = "my_test_routing_key"
    }

    @Bean
    fun queue(): Queue {
        return Queue(MY_TEST_PRODUCER_QUEUE_NAME)
    }

    @Bean
    fun exchange(): TopicExchange? {
        return TopicExchange(MY_TEST_PRODUCER_EXCHANGE_NAME)
    }

    @Bean
    fun binding(queue: Queue?, exchange: TopicExchange?): Binding? {
        return BindingBuilder.bind(queue).to(exchange).with(MY_TEST_PRODUCER_ROUTING_KEY)
    }

    @Bean
    fun rabbitTemplate(): RabbitTemplate? {
        val rabbitTemplate = RabbitTemplate(connectionFactory)
        rabbitTemplate.messageConverter = Jackson2JsonMessageConverter()
        return rabbitTemplate
    }
}