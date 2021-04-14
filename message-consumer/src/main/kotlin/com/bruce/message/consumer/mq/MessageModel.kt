package com.bruce.message.consumer.mq

import java.io.Serializable

class MessageModel<T> : Serializable {
    val id: String? = null
    val data: T? = null
}