package com.example.applaudochallenge.ui.widgets

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.material.*
import androidx.compose.foundation.shape.CircleShape

import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.toLowerCase
import com.example.applaudochallenge.ui.models.FiltersType
import com.example.applaudochallenge.ui.models.getFilterMap
import com.example.applaudochallenge.ui.models.getTvShowFilters

@OptIn(ExperimentalMaterialApi::class)
@Composable
internal fun FiltersPills(
    onFilterSelected: (String) -> Unit,
    filtersList: List<FiltersType> = getTvShowFilters(),
) {
    val selectedFilter = rememberSaveable { mutableStateOf(getFilterMap("")) }

    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        items(filtersList) {

            FilterChip(
                selected = selectedFilter.value == it,
                onClick = {
                    selectedFilter.value = getFilterMap(it.filterName)
                    onFilterSelected(it.filterName)
                },
                colors = ChipDefaults.filterChipColors(
                    selectedBackgroundColor = MaterialTheme.colors.primary,
                    selectedContentColor = Color.White
                ),
                shape = CircleShape,
                modifier = Modifier.padding(horizontal = 4.dp),
            ) {
                Text(
                    text = formatTvShowFilter(it.filterName),
                )
            }
        }
    }
}

private fun formatTvShowFilter(filterName: String): String {
    return filterName.replace("_", " ").toLowerCase(Locale.current).capitalize(Locale.current)
}
