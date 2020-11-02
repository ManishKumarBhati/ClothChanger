package com.bmk.daggerproject.domain

data class ResponseData(
    val id: String,
    val title: String,
    val imgUrl: String?

)

data class DetailsData(
    val id: String,
    val title: String,
    val imgUrl: String,
    val comment: String
)