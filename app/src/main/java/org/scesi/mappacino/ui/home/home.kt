package org.scesi.mappacino.ui.home

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(viewModel: HomeViewModel = koinViewModel() ){

    viewModel.toString()
    Text(text = "Hello map")
}