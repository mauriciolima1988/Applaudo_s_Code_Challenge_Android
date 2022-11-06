package com.example.applaudochallenge.ui.navigation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ChipDefaults
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FilterChip
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.unit.dp
import com.example.applaudochallenge.R
import com.example.applaudochallenge.ui.models.TvShowInfos.FiltersType
import com.example.applaudochallenge.ui.models.TvShowInfos.getFilterMap
import com.example.applaudochallenge.ui.models.TvShowInfos.getTvShowFilters

@OptIn(ExperimentalMaterialApi::class)
@Composable
internal fun FiltersChipGroup(
    modifier: Modifier = Modifier,
    filters: List<FiltersType> = getTvShowFilters(),
    onFilterSelected: (String) -> Unit
) {

    val defaultFilter = stringResource(id = R.string.default_tvshows_filter)
    val selectedFilter = rememberSaveable { mutableStateOf(getFilterMap(defaultFilter)) }

    Box(modifier = modifier.padding(4.dp)) {
        LazyRow {
            items(filters) { filter ->
                FilterChip(
                    modifier = Modifier.padding(horizontal = 2.dp),
                    selected = selectedFilter.value == filter,
                    onClick = {
                        selectedFilter.value = getFilterMap(filter.filterName)
                        onFilterSelected(filter.filterName)
                    },
                    shape = CircleShape,
                    colors = ChipDefaults.filterChipColors(
                        selectedBackgroundColor = MaterialTheme.colors.primary,
                        selectedContentColor = Color.White
                    )
                ) {
                    Text(
                        modifier = Modifier.padding(8.dp),
                        text = filter.filterName.capitalize(Locale.current).replace("_", " ")
                    )
                }
            }
        }
    }
}