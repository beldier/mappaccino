package org.scesi.mappacino

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import org.scesi.mappacino.ui.LocationService
import com.mapbox.geojson.Point
import kotlinx.coroutines.delay
import org.scesi.mappacino.ui.theme.MappacinoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MappacinoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MapScreen()
                }
            }
        }
    }
    @Composable
    fun MapScreen() {
        var point: Point? by remember {
            mutableStateOf(Point.fromLngLat( -66.14486399148288, -17.3932635439369))
        }
        var relaunch by remember {
            mutableStateOf(false)
        }
        val context = LocalContext.current

        val permissionRequest = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.RequestMultiplePermissions(),
            onResult = { permissions ->
                if (!permissions.values.all { it }) {
                    //handle permission denied
                }
                else {
                    relaunch = !relaunch
                }
            }
        )

        Column(
            modifier = Modifier.fillMaxSize(),
        ) {
            MapBoxMap(
                point = point,
                modifier = Modifier
                    .fillMaxSize()
            )
        }

        LaunchedEffect(key1 = relaunch) {
            try {
                // TODO use for current location
//                val location = LocationService().getCurrentLocation(context)
//                point = Point.fromLngLat(location.longitude, location.latitude)



            } catch (e: LocationService.LocationServiceException) {
                when (e) {
                    is LocationService.LocationServiceException.LocationDisabledException -> {
                        //handle location disabled, show dialog or a snack-bar to enable location
                    }

                    is LocationService.LocationServiceException.MissingPermissionException -> {
                        permissionRequest.launch(
                            arrayOf(
                                android.Manifest.permission.ACCESS_FINE_LOCATION,
                                android.Manifest.permission.ACCESS_COARSE_LOCATION
                            )
                        )
                    }

                    is LocationService.LocationServiceException.NoNetworkEnabledException -> {
                        //handle no network enabled, show dialog or a snack-bar to enable network
                    }

                    is LocationService.LocationServiceException.UnknownException -> {
                        //handle unknown exception
                    }
                }
            }
        }
    }
}

