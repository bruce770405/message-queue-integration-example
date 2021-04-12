package com.bruce.message.consumer.models.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class MessagePushLogResponse<T>(@JsonProperty(value = "data")
                                     var data: List<T?>,
                                     @JsonProperty(value = "page")
                                     private val paginationData: PaginationData?
)