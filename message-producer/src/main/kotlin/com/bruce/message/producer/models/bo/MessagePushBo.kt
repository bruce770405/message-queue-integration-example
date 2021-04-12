package com.bruce.message.producer.models.bo

import java.io.Serializable

data class MessagePushBo(val id: String, val title: String, val content: String) : Serializable
