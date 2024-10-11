package com.dev.capx.ui

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
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
    // Declaration
    private lateinit var stockViewModel: StockViewModel
    private lateinit var searchStock: EditText
    private lateinit var btnSearch: ImageButton
    private lateinit var stockName: TextView
    private lateinit var stockPrice: TextView
    private lateinit var percentageChange: TextView
    private lateinit var progressBar: ProgressBar
    private lateinit var loadingLayout: LinearLayout
    private lateinit var stockInfoCard: com.google.android.material.card.MaterialCardView
    private val API_KEY: String = "6709198546f986.42101963" // Replace with your API key
    private val BASE_URL: String = "https://eodhistoricaldata.com/api/real-time/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Initialize views
        searchStock = findViewById(R.id.searchStock)
        btnSearch = findViewById(R.id.btnSearch)
        stockName = findViewById(R.id.stockName)
        stockPrice = findViewById(R.id.stockPrice)
        percentageChange = findViewById(R.id.percentageChange)
        progressBar = findViewById(R.id.progressBar)
        loadingLayout = findViewById(R.id.loadingLayout)
        stockInfoCard = findViewById(R.id.stockInfoCard)

        // Initialize ViewModel
        val apiService = EodApiService.create(BASE_URL)
        val repository = StockRepository(apiService)
        val factory = StockViewModelFactory(repository)
        stockViewModel = factory.create(StockViewModel::class.java)

        // Observe LiveData from the ViewModel
        observeViewModel()

        // Set click listener for the search button
        btnSearch.setOnClickListener {
            // Get the stock symbol from the EditText
            val stockSymbol = searchStock.text.toString().trim()
            // Construct the full URL
            val fullUrl = "$BASE_URL$stockSymbol?api_token=$API_KEY&fmt=json"

            // Check if the stock symbol is not empty before making the API call
            if (stockSymbol.isNotEmpty()) {
                // Hide the keyboard when the search button is clicked
                hideKeyboard()
                stockViewModel.fetchStockData(fullUrl)
            } else {
                // Show a toast if the stock symbol is empty
                Toast.makeText(this, "Please enter a stock symbol", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun observeViewModel() {
        // Observe stockData LiveData
        stockViewModel.stockData.observe(this) { response ->
            response.body()?.let { stockInfo ->
                // Update UI with stock data
                updateUI(stockInfo)
            } ?: run {
                // Handle the case where the response body is null
                Toast.makeText(this, "Error retrieving stock data", Toast.LENGTH_SHORT).show()
            }
        }
        // Observe isLoading LiveData
        stockViewModel.isLoading.observe(this) { isLoading ->
            // Show/hide loading layout based on isLoading value
            loadingLayout.visibility = if (isLoading) View.VISIBLE else View.GONE
        }
        // Observe isSymbolFound LiveData
        stockViewModel.isSymbolFound.observe(this) { isFound ->
            if (!isFound) {
                // Show a toast if the stock symbol is not found
                Toast.makeText(this, "Stock symbol not found", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun updateUI(stockInfo: StockInfo) {
        // Update UI elements with stock data
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