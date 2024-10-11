package com.dev.capx.network

import com.dev.capx.model.StockInfo
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Url

// Retrofit service interface
interface EodApiService {
    // GET request to fetch stock data
    @GET
    suspend fun fetchStockData(
        // URL parameter for the full URL
        @Url fullUrl: String
    ): Response<StockInfo>
    // Companion object to create the service
    companion object {
        // Create a Retrofit instance
        fun create(baseUrl: String): EodApiService {
            // Configure and return the service
            return Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(EodApiService::class.java)
        }
    }
}
