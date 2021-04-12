package com.bruce.message.consumer.models.po

import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "message_push_log")
data class MessagePushLogEntity(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        var id: Long = -1,

        @Column(name = "title")
        var title: String = "",

        @Column(name = "content")
        var content: String = "",

        @Column(name = "added_at")
        var addedAt: LocalDateTime = LocalDateTime.now(),

        @Column(name = "update_at")
        var updateAt: LocalDateTime = LocalDateTime.now()
)