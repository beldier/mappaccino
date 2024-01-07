package org.scesi.mappacino

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.mapbox.common.MapboxOptions
import com.mapbox.geojson.Point
import com.mapbox.maps.MapInitOptions
import com.mapbox.maps.MapboxExperimental
import com.mapbox.maps.dsl.cameraOptions
import com.mapbox.maps.extension.compose.MapboxMap
import com.mapbox.maps.extension.compose.animation.viewport.MapViewportState
import com.mapbox.maps.extension.compose.animation.viewport.rememberMapViewportState
import com.mapbox.maps.plugin.animation.MapAnimationOptions
import com.mapbox.maps.plugin.compass.generated.CompassSettings
import com.mapbox.maps.plugin.scalebar.generated.ScaleBarSettings

val DEFAULT_POSITION: Point = Point.fromLngLat(-66.14486399148288, -17.3932635439369)
const val ANIMATION_DURATION:Long = 5000
@Composable
@OptIn(MapboxExperimental::class)
fun MainMapViewComposable(
    point: Point?,
) {
    MapboxOptions.accessToken = BuildConfig.MAPBOX_TOKEN
    val mapViewportState = rememberMapViewportState() {
        // TODO edit initial state , add animation to travel from city to college
        setCameraOptions {
            center(DEFAULT_POSITION)
            zoom(18.0)
            pitch(60.0)
        }
        MapAnimationOptions.mapAnimationOptions { duration(ANIMATION_DURATION) }
    }

    point?.let {
        flyTo(mapViewportState, it)
        MapAnimationOptions.mapAnimationOptions { duration(ANIMATION_DURATION) }
    }
    MapboxMap(
        modifier = Modifier.fillMaxSize(),
        compassSettings = CompassSettings {
            visibility = true // TODO hide or place the compass below the search bar
            position = 6
        },
        mapInitOptionsFactory = { context ->
            MapInitOptions(
                context = context,
                styleUri = BuildConfig.MAPBOX_STYLE_URL,
            )
        },
        scaleBarSettings = ScaleBarSettings {
            enabled = false
        },
        mapViewportState = mapViewportState

    )
}

private fun flyTo(mapViewportState: MapViewportState, point: Point) {
    mapViewportState.flyTo(
        cameraOptions = cameraOptions {
            center(point)
            zoom(18.0)
            pitch(60.0)
        },
        MapAnimationOptions.mapAnimationOptions { duration(ANIMATION_DURATION) }
    )
}