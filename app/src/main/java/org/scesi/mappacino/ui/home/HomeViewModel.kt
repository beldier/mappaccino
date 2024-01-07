package org.scesi.mappacino.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mapbox.geojson.Point
import com.mapbox.maps.CameraState
import com.mapbox.maps.MapboxExperimental
import com.mapbox.maps.dsl.cameraOptions
import com.mapbox.maps.extension.compose.animation.viewport.MapViewportState
import com.mapbox.maps.extension.compose.animation.viewport.rememberMapViewportState
import com.mapbox.maps.plugin.animation.MapAnimationOptions
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel
import org.scesi.mappacino.data.toError
import org.scesi.mappacino.domain.ClassroomLocationUI
import org.scesi.mappacino.domain.Error
import org.scesi.mappacino.domain.ISearchable
import org.scesi.mappacino.domain.SemesterUI
import org.scesi.mappacino.domain.SubjectUI
import org.scesi.mappacino.usescases.GetClassRoomCoordinateUseCase
import org.scesi.mappacino.usescases.GetSemestersUseCase
import org.scesi.mappacino.usescases.RequestSubjectsUseCase

@KoinViewModel
class HomeViewModel(
    getSemestersUseCase: GetSemestersUseCase,
    private val requestSubjectsUseCase: RequestSubjectsUseCase,
    private val getClassRoomCoordinateUseCase: GetClassRoomCoordinateUseCase
) : ViewModel() {


    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _state.asStateFlow()

    private val _mapState = MutableStateFlow(MapState())
    val mapState: StateFlow<MapState> = _mapState.asStateFlow()

    init {

        viewModelScope.launch {
            try {

                _state.value = _state.value.copy(loading = true)
                val error = requestSubjectsUseCase()
                _state.update {
                    _state.value.copy(loading = false, error = error)
                }

                getSemestersUseCase()
                    .catch { cause ->
                        _state.update {
                            it.copy(error = cause.toError())
                        }
                    }
                    .collect { semesters ->
                        _state.update { UiState(semesters = semesters) }
                    }
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }
    }

    fun updateSearchQuery(query: String) {
        viewModelScope.launch {
            val classroomsList = getClassRoomCoordinateUseCase(query)
            _state.update {
                _state.value.copy(searchQuery = query, searchList = classroomsList)
            }
        }
    }

    fun updatePoint(point: Point) {

        _mapState.update {
            _mapState.value.copy(point = AppPoint(id = Math.random(), point = point))
        }
    }


    data class UiState(
        val loading: Boolean = false,
        val searchList: List<ISearchable> = listOf(),
        val semesters: List<SemesterUI>? = null,
        val classRooms: List<ClassroomLocationUI> = listOf(),
        val error: Error? = null,
        val searchQuery: String = "",
    )

    data class MapState(
        val point: AppPoint = AppPoint(),
    )

    // TODO find another way to emit same values for coordinates
    data class AppPoint(
        val id: Double = 0.0,
        val point: Point? = null
    )
}