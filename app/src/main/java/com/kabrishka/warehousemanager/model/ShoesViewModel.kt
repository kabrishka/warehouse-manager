package com.kabrishka.warehousemanager.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kabrishka.warehousemanager.data.Datasource

class ShoesViewModel: ViewModel() {
    private val shoesLiveData = MutableLiveData<List<Shoe>>()
    val shoes: LiveData<List<Shoe>> get() = shoesLiveData

    fun initShoes() {
        shoesLiveData.value = Datasource().loadShoes() as MutableList<Shoe>
    }

    fun addShoes(shoe: Shoe) {
        shoesLiveData.value = shoesLiveData.value?.plus(shoe) ?: listOf(shoe)
    }

}