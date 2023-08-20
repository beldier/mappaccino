package org.scesi.mappacino.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel
import org.scesi.mappacino.usescases.RequestSubjectsUseCase

@KoinViewModel
class HomeViewModel(
    requestSubjectsUseCase: RequestSubjectsUseCase
): ViewModel() {
    init {
        viewModelScope.launch {
            requestSubjectsUseCase()
        }
    }
}