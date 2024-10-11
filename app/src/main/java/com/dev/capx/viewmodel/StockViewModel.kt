package com.dev.capx.viewmodel

import androidx.lifecycle.*
import com.dev.capx.model.StockInfo
import com.dev.capx.repository.StockRepository
import kotlinx.coroutines.launch
import retrofit2.Response

class StockViewModel(private val repository: StockRepository) : ViewModel() {

    private val _stockData = MutableLiveData<Response<StockInfo>>()
    val stockData: LiveData<Response<StockInfo>> get() = _stockData

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    fun fetchStockData(fullUrl: String) {
        _isLoading.value = true
        viewModelScope.launch {
            val response = repository.getStockData(fullUrl)
            _stockData.value = response
            _isLoading.value = false
        }
    }
}