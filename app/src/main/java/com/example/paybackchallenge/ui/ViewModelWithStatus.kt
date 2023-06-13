package com.example.paybackchallenge.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

open class ViewModelWithStatus : ViewModel() {

    var status by mutableStateOf<ModelStatus?>(null)
        protected set

    var errorStatus by mutableStateOf<ErrorStatus?>(ErrorStatus())
        protected set

    fun clearStatus() {
        status = null
    }

    fun updateStatus(status: ModelStatus) {
        this.status = status
    }

    fun setStatus(status: Status, message: String? = null) {
        this.status = ModelStatus(status, message)
        this.errorStatus = ErrorStatus()
    }

    fun handleNetworkError(e: Exception, isTokenCall: Boolean? = false) {
        status = ModelStatus(Status.ERROR)
    }
}