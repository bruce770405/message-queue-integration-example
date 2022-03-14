package com.bruce.message.producer.service.impl

import com.bruce.message.producer.service.RetryService
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service

@Service
class RetryServiceImpl:RetryService {

    @Scheduled
    override fun doHandleData() {

    }
}