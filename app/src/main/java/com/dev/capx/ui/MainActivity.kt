package com.dev.capx.ui

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.dev.capx.R
import com.dev.capx.model.StockInfo
import com.dev.capx.network.EodApiService
import com.dev.capx.repository.StockRepository
import com.dev.capx.viewmodel.StockViewModel
import com.dev.capx.viewmodel.StockViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var stockViewModel: StockViewModel
    private lateinit var searchStock: EditText
    private lateinit var btnSearch: ImageButton
    private lateinit var stockName: TextView
    private lateinit var stockPrice: TextView
    private lateinit var percentageChange: TextView
    private lateinit var progressBar: ProgressBar
    private lateinit var loadingLayout: LinearLayout
    private lateinit var stockInfoCard: com.google.android.material.card.MaterialCardView
    private val API_KEY: String = "6709198546f986.42101963"
    private val BASE_URL: String = "https://eodhistoricaldata.com/api/real-time/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val baseUrl = "https://eodhistoricaldata.com/api/real-time/" // base URL
        val apiService = EodApiService.create(baseUrl)
        val repository = StockRepository(apiService)
        val factory = StockViewModelFactory(repository)
        searchStock = findViewById(R.id.searchStock)
        btnSearch = findViewById(R.id.btnSearch)
        stockName = findViewById(R.id.stockName)
        stockPrice = findViewById(R.id.stockPrice)
        percentageChange = findViewById(R.id.percentageChange)
        progressBar = findViewById(R.id.progressBar)
        loadingLayout = findViewById(R.id.loadingLayout)
        stockInfoCard = findViewById(R.id.stockInfoCard)

        stockViewModel = factory.create(StockViewModel::class.java)

        // Observe LiveData from the ViewModel
        observeViewModel()

        btnSearch.setOnClickListener {
            val stockSymbol = searchStock.text.toString().trim()
            val fullUrl = "$BASE_URL$stockSymbol?api_token=$API_KEY&fmt=json" // Ensure it's CSV format

            if (stockSymbol.isNotEmpty()) {
                hideKeyboard()
                stockViewModel.fetchStockData(fullUrl)
            } else {
                Toast.makeText(this, "Please enter a stock symbol", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun observeViewModel() {
        stockViewModel.stockData.observe(this) { response ->
            println(response.body())
            response.body()?.let { stockInfo ->
                updateUI(stockInfo)
            } ?: run {
                Toast.makeText(this, "Error retrieving stock data", Toast.LENGTH_SHORT).show()
            }
        }

        stockViewModel.isLoading.observe(this) { isLoading ->
            loadingLayout.visibility = if (isLoading) View.VISIBLE else View.GONE
        }
    }

    private fun updateUI(stockInfo: StockInfo) {
        stockInfoCard.visibility = View.VISIBLE
        stockName.text = "${stockInfo.code}"
        stockPrice.text = "$${stockInfo.close}"
        percentageChange.text = "${stockInfo.change_p}%"
    }
    fun hideKeyboard() {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        val view = currentFocus ?: View(this)
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}