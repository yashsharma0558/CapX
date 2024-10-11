package com.dev.capx.repository

import com.dev.capx.model.StockInfo
import com.dev.capx.network.EodApiService
import retrofit2.Response

// Repository class for fetching stock data
class StockRepository(private val apiService: EodApiService) {
    // Function to fetch stock data from the API
    suspend fun getStockData(fullUrl: String): Response<StockInfo> {
        return apiService.fetchStockData(fullUrl)
    }
}
