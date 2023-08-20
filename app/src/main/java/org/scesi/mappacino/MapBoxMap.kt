package org.scesi.mappacino

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.viewinterop.NoOpUpdate
import androidx.core.graphics.drawable.toBitmap
import com.mapbox.common.MapboxOptions
import com.mapbox.geojson.Point
import com.mapbox.maps.CameraBoundsOptions
import com.mapbox.maps.CameraOptions
import com.mapbox.maps.CoordinateBounds
import com.mapbox.maps.MapInitOptions
import com.mapbox.maps.MapView
import com.mapbox.maps.MapboxMapsOptions.tileStore
import com.mapbox.maps.TileStoreUsageMode
import com.mapbox.maps.mapsOptions
import com.mapbox.maps.plugin.annotation.annotations
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationManager
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationOptions
import com.mapbox.maps.plugin.annotation.generated.createPointAnnotationManager

@Composable
fun MapBoxMap(
    modifier: Modifier = Modifier,
    point: Point?,
) {
//    val context = LocalContext.current
//    val marker = remember(context) {
//        context.getDrawable(R.drawable.marker)!!.toBitmap()
//    }
//    var pointAnnotationManager: PointAnnotationManager? by remember {
//        mutableStateOf(null)
//    }
//    MapboxOptions.accessToken = "PLACE YOUR TOKEN HERE"
//
//    AndroidView(
//        factory = {
//            MapView(
//                it,
//                MapInitOptions(context = context)
//            ).also { mapView ->
//
//                mapView.getMapboxMap()
//                    .loadStyleUri("PLACE YOUR URL HERE")
//                mapView.getMapboxMap().setBounds(
//                    CameraBoundsOptions.Builder()
//                        .bounds(
//                            CoordinateBounds(
//                                Point.fromLngLat(-66.1490322325618, -17.396410324535587),
//                                Point.fromLngLat(-66.14115936192975, -17.390951243427203),
//                                false
//                            )
//                        )
//                        .build()
//                )
//                val annotationApi = mapView.annotations
//                pointAnnotationManager = annotationApi.createPointAnnotationManager()
//            }
//        },
//        update = { mapView ->
//            if (point != null) {
//                pointAnnotationManager?.let {
//                    it.deleteAll()
//                    val pointAnnotationOptions = PointAnnotationOptions()
//                        .withPoint(point)
//                        .withIconImage(marker)
//
//
//                    it.create(pointAnnotationOptions)
//                    mapView.getMapboxMap()
//                        .setCamera(
//                            CameraOptions.Builder().zoom(18.0).pitch(60.0).center(point).build()
//                        )
//                }
//            }
//            NoOpUpdate
//        },
//        modifier = modifier
//    )
}