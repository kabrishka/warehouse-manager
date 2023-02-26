package com.kabrishka.warehousemanager

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivityViewModel: ViewModel() {
    private val stateLiveData = MutableLiveData<Boolean>()
    val state: LiveData<Boolean> get() = stateLiveData

    fun initState() {
        stateLiveData.value = false
    }

    fun logout() {
        stateLiveData.value = true
    }
}