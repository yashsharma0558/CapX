package com.dev.capx.repository

import com.dev.capx.model.StockInfo
import com.dev.capx.network.EodApiService
import retrofit2.Response


class StockRepository(private val apiService: EodApiService) {

    suspend fun getStockData(fullUrl: String): Response<StockInfo> {
        return apiService.fetchStockData(fullUrl)
    }
}
