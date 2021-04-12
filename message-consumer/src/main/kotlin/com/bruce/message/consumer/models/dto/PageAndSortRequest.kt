package com.bruce.message.consumer.models.dto

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort

data class PageAndSortRequest(
        @JsonProperty("current")
        var currentPage: Int = 1,
        @JsonProperty("per")
        val perPage: Int = 10,
        @JsonProperty("sort_col")
        val sortCol: String = "id",
        @JsonProperty("order")
        var order: Sort.Direction) {

    fun convertToPageable(): Pageable {
        return convertToPageable(sortCol, order)
    }

    fun convertToPageable(sortCol: String?, order: Sort.Direction?): Pageable {
        val sort: Sort = when (order) {
            Sort.Direction.ASC -> Sort.by(sortCol).ascending()
            Sort.Direction.DESC -> Sort.by(sortCol).descending()
            else -> Sort.by(sortCol).descending()
        }
        return PageRequest.of(this.currentPage, this.perPage, sort)
    }
}