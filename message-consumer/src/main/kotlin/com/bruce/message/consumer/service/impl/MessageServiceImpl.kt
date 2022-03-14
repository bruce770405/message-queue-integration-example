package com.bruce.message.consumer.service.impl

import com.bruce.message.consumer.models.bo.MessagePushBo
import com.bruce.message.consumer.models.bo.MessagePushLogBo
import com.bruce.message.consumer.models.dto.MessagePushLogResponse
import com.bruce.message.consumer.models.dto.PaginationData
import com.bruce.message.consumer.models.po.MessagePushLogEntity
import com.bruce.message.consumer.repository.MessagePushLogRepository
import com.bruce.message.consumer.service.MessageService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.stereotype.Service
import java.time.Instant
import java.time.LocalDateTime
import java.util.*

@Service
class MessageServiceImpl(
    private val messagePushLogRepository: MessagePushLogRepository,
    private val redisTemplate: StringRedisTemplate
) : MessageService {

    private val logger: Logger = LoggerFactory.getLogger(javaClass)

    override fun doHandleMessage(messagePushBo: MessagePushBo) {

        // do something
        logger.info("do handle something... ${messagePushBo.id} , title : ${messagePushBo.title} , content : ${messagePushBo.content}")

        if (messagePushBo.id.isNullOrEmpty()) {
            return
        }

        if (redisTemplate.opsForValue().get(messagePushBo.id).isNullOrEmpty()) {
            // handle
            val messagePushLogEntity = MessagePushLogEntity()
            messagePushLogEntity.title = messagePushBo.title.orEmpty()
            messagePushLogEntity.content = messagePushBo.content.orEmpty()
            messagePushLogEntity.addedAt = LocalDateTime.now()
            messagePushLogEntity.updateAt = LocalDateTime.now()
            messagePushLogRepository.save(messagePushLogEntity)

            //
            redisTemplate.opsForValue().set(messagePushBo.id, "0")
        }

    }

    override fun getMessagePushHandleLog(
        startTime: Long,
        endTime: Long,
        pageable: Pageable
    ): MessagePushLogResponse<MessagePushLogBo> {
        val start: LocalDateTime =
            LocalDateTime.ofInstant(Instant.ofEpochMilli(startTime), TimeZone.getDefault().toZoneId())
        val end: LocalDateTime =
            LocalDateTime.ofInstant(Instant.ofEpochMilli(endTime), TimeZone.getDefault().toZoneId())
        val page: Page<MessagePushLogEntity> = messagePushLogRepository.findAllByAddedAtBetween(start, end, pageable)
        val messagePushLogBoList: List<MessagePushLogBo> =
            page.content.map { entity -> MessagePushLogBo(entity.id, entity.title, entity.content, entity.addedAt) }
        return MessagePushLogResponse(
            messagePushLogBoList,
            PaginationData(pageable.pageNumber, page.totalPages, page.totalElements)
        )
    }

}
