package org.scesi.mappacino.ui.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.zIndex
import com.mapbox.geojson.Point
import org.koin.androidx.compose.koinViewModel
import org.scesi.mappacino.MainMapViewComposable
import org.scesi.mappacino.ui.shared.DockedSearchBarComponent


@Composable
fun HomeScreen(viewModel: HomeViewModel = koinViewModel()) {
    val dataState by viewModel.state.collectAsState()
    val mapState by viewModel.mapState.collectAsState()
    Box(
        modifier = Modifier.fillMaxSize(),
    ) {
        Box(
            modifier = Modifier
                .zIndex(1f)
                .fillMaxWidth()
        ) {
            DockedSearchBarComponent(
                searchQuery = dataState.searchQuery,
                updateQuery = { viewModel.updateSearchQuery(it) },
                searchList = dataState.searchList,
                onItemClick = { viewModel.updatePoint(Point.fromLngLat(it.longitude, it.latitude)) }
            )
        }
        MainMapViewComposable(mapState.point)
    }
}