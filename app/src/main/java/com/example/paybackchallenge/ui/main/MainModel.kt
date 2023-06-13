package com.example.paybackchallenge.ui.main

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.example.paybackchallenge.domain.entities.User
import com.example.paybackchallenge.ui.ViewModelWithStatus
import com.example.paybackchallenge.usecases.MainUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class MainModel @Inject constructor(
    private val mainUseCase: MainUseCase
) : ViewModelWithStatus() {

    var state by mutableStateOf(MainState())
        private set

    init {
        startSplash()
        requestUser()
    }

    private fun setLoading(loading: Boolean) {
        state = state.copy(loading = loading)
    }

    private fun startSplash() {
        viewModelScope.launch {
            delay(35000)
            setShowSplash(false)
        }
    }


    private fun setUser(user: User) {
        state = state.copy(user = user)
    }

    private fun requestUser() = viewModelScope.launch {
        try {
            setLoading(true)
            withContext(Dispatchers.IO) {
                mainUseCase.getUser()
            }.also { setUser(it) }
        } catch (e: Exception) {
            handleNetworkError(e)
        } finally {
            setLoading(false)
        }
    }


    private fun setShowSplash(showSplash: Boolean){
        state = state.copy(showSplash = showSplash)
    }

}