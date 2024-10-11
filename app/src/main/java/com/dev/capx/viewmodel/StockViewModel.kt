package com.dev.capx.viewmodel

import androidx.lifecycle.*
import com.dev.capx.model.StockInfo
import com.dev.capx.repository.StockRepository
import kotlinx.coroutines.launch
import retrofit2.Response

class StockViewModel(private val repository: StockRepository) : ViewModel() {
    // LiveData to hold stock data
    private val _stockData = MutableLiveData<Response<StockInfo>>()
    val stockData: LiveData<Response<StockInfo>> get() = _stockData
    // LiveData to track loading state
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading
    // LiveData to track symbol search result
    private val _isSymbolFound = MutableLiveData<Boolean>()
    val isSymbolFound: LiveData<Boolean> get() = _isSymbolFound

    // Function to fetch stock data from the repository
    fun fetchStockData(fullUrl: String) {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                // Make the API call using the repository
                val response = repository.getStockData(fullUrl)
                if (response.isSuccessful && response.body() != null) {
                    // Update LiveData with the response
                    _stockData.value = response
                    _isSymbolFound.value = true
                    _isLoading.value = false
                } else {
                    // Handle unsuccessful response
                    _isSymbolFound.value = false
                    _isLoading.value = false
                }
            } catch (e: Exception) {
                // Handle exceptions (e.g., network errors)
                _isSymbolFound.value = false
                _isLoading.value = false
            }
        }
    }
}