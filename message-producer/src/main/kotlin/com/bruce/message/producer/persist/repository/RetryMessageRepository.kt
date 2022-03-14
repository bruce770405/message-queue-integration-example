package com.bruce.message.producer.persist.repository

import com.bruce.message.producer.persist.entity.RetryMessageEntity
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface RetryMessageRepository : CrudRepository<RetryMessageEntity, String> {
}
