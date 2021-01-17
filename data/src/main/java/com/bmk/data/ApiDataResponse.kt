package com.bmk.data

import com.google.gson.annotations.SerializedName


data class ApiDataResponse(
    @JvmField @SerializedName("data") val data: List<ImgUrlResponse>
)

data class ImgUrlResponse(
    @JvmField @SerializedName("id") val id: String,
    @JvmField @SerializedName("images") val images: List<ImagesItem>?,
    @JvmField @SerializedName("title") val title: String
)

data class ImagesItem(
    @JvmField @SerializedName("link") val link: String,
    @JvmField @SerializedName("animated") val animated: Boolean,
    @JvmField @SerializedName("id") val id: String
)
