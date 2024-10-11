package com.dev.capx.network

import com.dev.capx.model.StockInfo
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Url

interface EodApiService {

    @GET
    suspend fun fetchStockData(
        @Url fullUrl: String // Full URL provided at runtime
    ): Response<StockInfo>

    companion object {
        fun create(baseUrl: String): EodApiService {
            return Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(EodApiService::class.java)
        }
    }
}
