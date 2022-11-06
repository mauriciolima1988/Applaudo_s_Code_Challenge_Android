package com.example.applaudochallenge.utilities

import com.example.applaudochallenge.BuildConfig

fun String.getImageByPath(): String {
    val baseUrl = BuildConfig.PICTURES_BASE_URL
    return "$baseUrl${this}"
}