package com.bruce.message.consumer.models.dto

import kotlin.math.ceil

data class PaginationData(
        /** How many records are in a page.  */
        val perPageSize: Int,

        /** The current page index.  */
        var currentPage: Int,

        /** How many pages are there.  */
        val totalCount: Long,
) {

    /** How many pages are there.  */
    private var totalPage: Int

    init {
        this.currentPage = currentPage + 1
        this.totalPage = if (totalCount == 0L) 1 else ceil(totalCount.toDouble() / perPageSize.toDouble()).toInt()
    }
}