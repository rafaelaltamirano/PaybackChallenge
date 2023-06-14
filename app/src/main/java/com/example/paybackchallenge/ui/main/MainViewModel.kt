package com.example.paybackchallenge.ui.main

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.example.paybackchallenge.ui.ViewModelWithStatus
import com.example.paybackchallenge.usecases.MainUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val mainUseCase: MainUseCase
) : ViewModelWithStatus() {

    var state by mutableStateOf(MainState())
        private set

    init {
        startSplash()
    }

    private fun setLoading(loading: Boolean) {
        state = state.copy(loading = loading)
    }

    private fun startSplash() {
        viewModelScope.launch {
            delay(5000)
            setShowSplash(false)
        }
    }


    private fun setShowSplash(showSplash: Boolean){
        state = state.copy(showSplash = showSplash)
    }

}