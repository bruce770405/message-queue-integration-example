package com.bruce.message.producer.service

import com.bruce.message.producer.models.bo.MessagePushBo

interface MessageService {

    fun pushAndHandleMessage(messagePushBo: MessagePushBo)

}