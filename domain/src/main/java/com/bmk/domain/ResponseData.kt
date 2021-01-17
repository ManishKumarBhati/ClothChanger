package com.bmk.domain

data class ResponseData(
    @JvmField val id: String,
    @JvmField val title: String,
    @JvmField val imgUrl: String?
)

data class DetailsData(
    @JvmField val id: String,
    @JvmField val title: String,
    @JvmField val imgUrl: String,
    @JvmField val comment: String
)