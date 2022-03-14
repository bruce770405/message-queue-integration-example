package com.bruce.message.producer.persist.entity

import com.bruce.message.producer.models.bo.MessagePushBo
import org.springframework.data.redis.core.RedisHash

@RedisHash("confirm-message")
data class ConfirmMessage(
    val id: String,
    val data: MessagePushBo
)