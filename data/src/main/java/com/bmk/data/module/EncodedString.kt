package com.bmk.data.module

data class EncodedString(
    private val data: String
) {
    override fun toString(): String {
        return data
    }
}