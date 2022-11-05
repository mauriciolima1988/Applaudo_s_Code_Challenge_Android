package com.example.applaudochallenge.ui.model

data class MoviesList(
    val page: Int,
    val results: List<Movies>,
    val total_pages: Int,
    val total_results: Int
)
