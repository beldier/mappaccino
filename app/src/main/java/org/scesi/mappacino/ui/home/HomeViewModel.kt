package org.scesi.mappacino.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel
import org.scesi.mappacino.data.toError
import org.scesi.mappacino.domain.Error
import org.scesi.mappacino.domain.SemesterUI
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

    init {

        viewModelScope.launch {
            try {

                _state.value = _state.value.copy(loading = true)
                val error = requestSubjectsUseCase()
                _state.update {
                    _state.value.copy(loading = false, error = error)
                }
                
                val classroom = getClassRoomCoordinateUseCase("617")

                classroom?.let { it.id }
                getSemestersUseCase()
                    .catch { cause -> _state.update {
                        it.copy(error = cause.toError()) } }
                    .collect { semesters ->
                        _state.update { UiState(semesters = semesters) }
                    }



            } catch (e: Exception) {
                e.printStackTrace()
            }

        }
    }


    data class UiState(
        val loading: Boolean = false,
        val semesters: List<SemesterUI>? = null,
        val error: Error? = null
    )
}