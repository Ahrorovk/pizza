package com.pizza.app.ui.screen.main

import android.util.Log
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(

) : ViewModel() {
    val selectedPizzaParam = MutableStateFlow(PizzaParam())
    val historyList = MutableStateFlow(mutableListOf<HistoryItem>())

    fun setPizzaParam(pizzaParam: PizzaParam) {
        Log.e("LOG", "PIZZA->$pizzaParam")
        selectedPizzaParam.update {
            it.copy(
                name = pizzaParam.name,
                price = pizzaParam.price,
                weight = pizzaParam.weight,
                imageId = pizzaParam.imageId,
                category = pizzaParam.category,
                id = pizzaParam.id
            )
        }
    }
}

data class HistoryItem(
    val sum: Int,
    val address: String,
    val phoneNumber: String,
    val date: String,
    val imageId: Int,
    val category: String
)