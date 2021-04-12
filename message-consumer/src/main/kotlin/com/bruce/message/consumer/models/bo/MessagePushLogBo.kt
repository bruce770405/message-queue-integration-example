package com.bruce.message.consumer.models.bo

import java.time.LocalDateTime

data class MessagePushLogBo(val id: Long, val title: String, val content: String, val handleTime: LocalDateTime) {
}