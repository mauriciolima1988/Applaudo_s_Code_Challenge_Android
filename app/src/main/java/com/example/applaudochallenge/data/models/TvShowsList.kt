package com.example.applaudochallenge.data.models

data class TvShowsList(
    val page: Int,
    val results: List<TvShow>,
    val total_pages: Int,
    val total_results: Int
)
