package com.example.paybackchallenge.ui.screens.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.example.paybackchallenge.domain.entities.Car
import com.example.paybackchallenge.domain.entities.Image
import com.example.paybackchallenge.domain.entities.SuperCharges
import com.example.paybackchallenge.ui.ViewModelWithStatus
import com.example.paybackchallenge.usecases.HomeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception
import javax.inject.Inject


@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val homeUseCase: HomeUseCase
) : ViewModelWithStatus() {

    var state by mutableStateOf(HomeState())
        private set

    init {
        requestCar()
        requestCharges()
        requestImages("fruits")
    }

    private fun setLoading(loading: Boolean) {
        state = state.copy(loading = loading)
    }

    private fun setCar(car: Car) {
        state = state.copy(car = car)
    }

    private fun setSuperCharges(listSuperCharges: List<SuperCharges>) {
        state = state.copy(listSuperCharges = listSuperCharges)
    }


    private fun setImages(images: List<Image>) {
        state = state.copy(imagesList = images)
    }


    private fun requestCar() = viewModelScope.launch {
        try {
            setLoading(true)
            withContext(Dispatchers.IO) {
                homeUseCase.getCar()
            }.also { setCar(it) }
        } catch (e: Exception) {
            handleNetworkError(e)
        } finally {
            setLoading(false)
        }
    }

    private fun requestCharges() = viewModelScope.launch {
        try {
            setLoading(true)
            withContext(Dispatchers.IO) {
                homeUseCase.getSuperCharges()
            }.also { setSuperCharges(it) }
        } catch (e: Exception) {
            handleNetworkError(e)
        } finally {
            setLoading(false)
        }
    }


     fun requestImages(text : String) = viewModelScope.launch {
        try {
            setLoading(true)
            withContext(Dispatchers.IO) {
                homeUseCase.searchImages(text)
            }.also { setImages(it) }
        } catch (e: Exception) {
            handleNetworkError(e)
        } finally {
            setLoading(false)
        }
    }

}