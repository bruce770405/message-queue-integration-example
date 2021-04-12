package com.bruce.message.consumer.repository

import com.bruce.message.consumer.models.po.MessagePushLogEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

@Repository
interface MessagePushLogRepository : JpaRepository<MessagePushLogEntity, Long> {

    fun findAllByAddedAtBetween(start: LocalDateTime, end: LocalDateTime, pageable: Pageable): Page<MessagePushLogEntity>
}