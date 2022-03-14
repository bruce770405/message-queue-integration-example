package com.bruce.message.producer.persist.repository

import com.bruce.message.producer.persist.entity.ConfirmMessage
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface MessageConfirmRepository : JpaRepository<ConfirmMessage, String> {
    override fun findById(id: String): Optional<ConfirmMessage>
}