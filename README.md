# Stock Lookup 📈

A lightweight Android application that allows users to quickly look up real-time stock information using the EODHD API.

![Minimum SDK Version](https://img.shields.io/badge/Min%20SDK-21-brightgreen)
![Kotlin Version](https://img.shields.io/badge/Kotlin-1.8.0-blue)
![License](https://img.shields.io/badge/License-MIT-green)

## Features ✨

- Real-time stock price lookup
- Company information display
- Percentage change tracking
- Clean, minimalist single-screen interface
- Smooth loading animations

## Screenshots 📱

[Place your screenshots here]

## Technologies Used 🛠️

- **Language:** Kotlin
- **Architecture:** MVVM (Model-View-ViewModel)
- **API Integration:** Retrofit
- **Stock Data:** EODHD API
- **Minimum SDK:** 21 (Android 5.0)

## Architecture Overview 🏗️

The app follows MVVM architecture pattern with clear separation of concerns:

```
app/
├── data/
│   ├── api/
│   │   ├── EODHDService.kt
│   │   └── StockApiClient.kt
│   ├── model/
│   │   └── Stock.kt
│   └── repository/
│       └── StockRepository.kt
├── ui/
│   └── main/
│       ├── MainActivity.kt
│       └── MainViewModel.kt
└── utils/
    └── Resource.kt
```

## Setup ⚙️

### Prerequisites

- Android Studio Arctic Fox or newer
- Android SDK 21 or higher
- EODHD API Key (Get it from [EODHD Website](https://eodhd.com))

### Installation

1. Clone the repository:
```bash
git clone https://github.com/yourusername/stock-lookup.git
```

2. Open the project in Android Studio

3. Create a `local.properties` file in the root directory and add your EODHD API key:
```properties
EODHD_API_KEY=your_api_key_here
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
    // AndroidX
    implementation 'androidx.core:core-ktx:1.8.0'
    implementation 'androidx.appcompat:appcompat:1.5.0'
    implementation 'androidx.lifecycle:lifecycle-viewmodel-ktx:2.5.0'
    
    // Retrofit
    implementation 'com.squareup.retrofit2:retrofit:2.9.0'
    implementation 'com.squareup.retrofit2:converter-gson:2.9.0'
    
    // Coroutines
    implementation 'org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4'
    implementation 'org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4'
}
```

## Contributing 🤝

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## License 📄

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Acknowledgments 🙏

- [EODHD API](https://eodhd.com) for providing stock market data
- [Android Documentation](https://developer.android.com) for development guidelines
- [Material Design](https://material.io) for design inspiration

## Contact 📬

Your Name - [@yourtwitter](https://twitter.com/yourtwitter)

Project Link: [https://github.com/yourusername/stock-lookup](https://github.com/yourusername/stock-lookup)

---
⭐️ Star us on GitHub — it motivates us a lot!
