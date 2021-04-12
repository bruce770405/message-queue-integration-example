package com.bruce.message.producer.mq

import java.io.Serializable

data class MessageModel<T>(val id: String, val data: T) : Serializable {
}