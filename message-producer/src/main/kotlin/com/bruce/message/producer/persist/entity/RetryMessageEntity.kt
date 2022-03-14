package com.bruce.message.producer.persist.entity

import javax.persistence.*


/**
 * retry message entity
 * @author  BruceHsu
 * @version 1.0, 2022/3/10
 * @see
 * @since
 */
@Entity
@Table(name = "RETRY_MESSAGE")
class RetryMessageEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    val id: String,

    @Column(name = "MESSAGE", nullable = false)
    val message: String,

    @Column(name = "MESSAGE", nullable = false)
    val enable: Boolean
);