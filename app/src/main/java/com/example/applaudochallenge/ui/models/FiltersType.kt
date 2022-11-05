package com.example.applaudochallenge.ui.models

enum class FiltersType(val filterName: String) {
    Popular("popular"),
    TopRated("top_rated"),
    OnTV("on_the_air"),
    AiringToday("airing_today")
}

fun getTvShowFilters(): List<FiltersType> {
    return listOf(
        FiltersType.Popular,
        FiltersType.TopRated,
        FiltersType.OnTV,
        FiltersType.AiringToday,
    )
}

fun getFilterMap(value: String): FiltersType? {
    val map = FiltersType.values().associateBy(FiltersType::filterName)
    return map[value]
}
