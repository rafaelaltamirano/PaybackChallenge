package com.example.paybackchallenge.ui.screens.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.example.paybackchallenge.domain.entities.Car
import com.example.paybackchallenge.domain.entities.Image
import com.example.paybackchallenge.domain.entities.SuperCharges
import com.example.paybackchallenge.ui.ViewModelWithStatus
import com.example.paybackchallenge.usecases.HomeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeUseCase: HomeUseCase,
    pager: Pager<Int, Image>
) : ViewModelWithStatus() {

    var state by mutableStateOf(HomeState())
        private set

    init {
//        requestImages("fruits")
    }

    private fun setLoading(loading: Boolean) {
        state = state.copy(loading = loading)
    }


    private fun setImages(images: List<Image>) {
        state = state.copy(imagesList = images)
    }

    fun setSelectedItem(itemImage: Image) {
        state = state.copy(itemImage = itemImage)
    }

    fun setDialogState(openDialog: Boolean) {
        state = state.copy(openDialog = openDialog)
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
    fun getBreakingNews(): Flow<PagingData<Image>> = homeUseCase.getImages().cachedIn(viewModelScope)


    //falta injectar el pager y probar que ande desde la screen 
    val beerPagingFlow = pager
        .flow
        .map { pagingData ->
            pagingData.map { it }
        }
        .cachedIn(viewModelScope)

}