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
import com.example.applaudochallenge.R
import com.example.applaudochallenge.data.models.tvshowdetails.FiltersType
import com.example.applaudochallenge.data.models.tvshowdetails.getFilterMap
import com.example.applaudochallenge.data.models.tvshowdetails.getTvShowFilters
import com.example.applaudochallenge.ui.theme.dimension

@OptIn(ExperimentalMaterialApi::class)
@Composable
internal fun FiltersChipGroup(
    modifier: Modifier = Modifier,
    filters: List<FiltersType> = getTvShowFilters(),
    onFilterSelected: (String) -> Unit
) {

    val defaultFilter = stringResource(id = R.string.default_tvshows_filter)
    val selectedFilter = rememberSaveable { mutableStateOf(getFilterMap(defaultFilter)) }

    Box(modifier = modifier.padding(MaterialTheme.dimension.sizeDp4)) {
        LazyRow {
            items(filters) { filter ->
                FilterChip(
                    modifier = Modifier.padding(horizontal = MaterialTheme.dimension.sizeDp2),
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
                        modifier = Modifier.padding(MaterialTheme.dimension.sizeDp8),
                        text = filter.filterName.capitalize(Locale.current).replace("_", " ")
                    )
                }
            }
        }
    }
}