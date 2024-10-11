package com.dev.capx.model

// Data class representing stock information
data class StockInfo(
    val code: String,
    val close: Double,
    val change_p: Double
)
