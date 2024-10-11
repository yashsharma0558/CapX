# CapX - Stock Lookup 📈

A lightweight Android application that allows users to quickly look up real-time stock information using the EODHD API.

![Minimum SDK Version](https://img.shields.io/badge/Min%20SDK-24-brightgreen)
![Kotlin Version](https://img.shields.io/badge/Kotlin-1.8.0-blue)

## Features ✨

- Real-time stock price lookup
- Company information display
- Percentage change tracking
- Clean, minimalist single-screen interface
- Smooth loading animations

## Screenshots 📱

![capxss](https://github.com/user-attachments/assets/61b600d7-ef0d-4d52-8bff-03d38325dc0c)


## Technologies Used 🛠️

- **Language:** Kotlin
- **Architecture:** MVVM (Model-View-ViewModel)
- **API Integration:** Retrofit
- **Stock Data:** EODHD API
- **Minimum SDK:** 24 (Android 7.0)

## Architecture Overview 🏗️

The app follows MVVM architecture pattern with clear separation of concerns:

```
capx/
├── model/
│   ├── StockInfo.kt
├── network/
│   ├── EodApiService.kt
├── repository/
│   └── StockRepository.kt
└── ui/
│   └── MainActivity.kt
└── viewmodel/
    └── StockViewModel.kt
    └── StockViewModelFactory.kt
```

### Prerequisites

- Android Studio Koala or newer
- Android SDK 24 or higher
- EODHD API Key (Get it from [EODHD Website](https://eodhd.com))

### Installation
#### Method 1 - Download apk form Google Drive
[Google Drive Link](https://drive.google.com/file/d/1VVOKzV87ZADgNMwHyQwCMlFLhkr1m8iN/view?usp=drive_link)

#### Method 2 - Manual Installation
1. Clone the repository:
```bash
git clone https://github.com/yashsharma0558/CapX.git
```

2. Open the project in Android Studio

3. Navigate to MainActivity.kt and replace the API_KEY value with your EOHD API key:
```MainActivity.kt
private val API_KEY: String = "your_api_key_here"
```

4. Build and run the project

## Usage 📱

1. Launch the app
2. Enter a stock symbol (e.g., AAPL for Apple Inc.)
3. Tap the search button
4. View the real-time stock information

## Dependencies 📚

```gradle
dependencies {
    // Retrofit
    implementation 'com.squareup.retrofit2:retrofit:2.9.0'
    implementation 'com.squareup.retrofit2:converter-gson:2.9.0'
    
    // Coroutines
    implementation 'org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4'
    implementation 'org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4'
}
```

## Acknowledgments 🙏

- [EODHD API](https://eodhd.com) for providing stock market data
- [Android Documentation](https://developer.android.com) for development guidelines
- [Material Design](https://material.io) for design inspiration

## Contact 📬

Your Name - [Yash Sharma](https://www.linkedin.com/in/yash-sharma-b802a2251)

Project Link: [https://github.com/yashsharma0558/CapX](https://github.com/yashsharma0558/CapX)

