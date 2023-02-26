package com.kabrishka.warehousemanager.data

import com.kabrishka.warehousemanager.model.Shoe

class Datasource {
    fun loadShoes(): List<Shoe> {
        return listOf<Shoe>(
            Shoe("Кроссовки","АX23456", "Adidas", "8UK"),
            Shoe("Кроссовки","АX33478", "Adidas", "9UK"),
            Shoe("Сланцы","BF23456", "Rebook", "4UK"),
            Shoe("Сланцы","BG22446", "Puma", "4UK"),
            Shoe("Ботинки","DX23456", "Puma", "5UK"),
            Shoe("Ботинки","DX23456", "Puma", "9UK"),
        )
    }
}