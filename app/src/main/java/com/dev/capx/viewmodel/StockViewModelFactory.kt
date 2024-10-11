package com.dev.capx.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dev.capx.repository.StockRepository

// ViewModelFactory to create StockViewModel
class StockViewModelFactory(
    private val repository: StockRepository
) : ViewModelProvider.Factory {

    // Create a new instance of StockViewModel
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        // Check if the model class is assignable from StockViewModel
        if (modelClass.isAssignableFrom(StockViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            // Create and return a new instance of StockViewModel
            return StockViewModel(repository) as T
        }
        // Throw an exception if the model class is not recognized
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
