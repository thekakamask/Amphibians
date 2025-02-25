# 🐸 **Amphibians**
**Amphibians** is a modern Android application developed with Jetpack Compose, displaying a list of amphibians, along with details about them and their images using a REST API. The data will be retrieved from the Internet via a network request and will contain the name, type, description and image URL of each amphibian. The project follows Android development best practices, implementing MVVM architecture and using tools like Retrofit for network calls.

## ✅ **LAST MAJOR UPDATES**
   - Replacement of the use of local data by dynamic retrieval of amphibians from the REST API.
   - Addition of a Repository (AmphibiansRepository) to centralize network calls and manage data retrieval via Retrofit.
   - Updated the ViewModel (AmphibiansViewModel) to integrate improved status management (Loading, Success, Error).
   - Dynamic extraction and management of amphibian types retrieved directly from the API (no need for a static enum list).
   - Optimized error handling: added logs and a more reactive UI in the event of query failure.
   - Corrected centering of elements (Text, Image) in the LazyVerticalGrid.

## ❌ **NEXT UPDATES**
   - Re implement the filter for the type of amphibian (who now don't work with REST API data).
   - Merge local data and REST API data and display all (at now REST API data only displayed).
   - Extract image from REST API with Coil (for the REST API data).

## 📋 **Features**
   - 🐸 Display a list of Amphibians :

      - 🟩 **IN PROGRESS** Retrieves and displays a list of amphibians via a REST API. ( at now app uses local datas)
      - ✅ **DONE** Shows detailed information including name, type, description, and image.

   - 🎨 **Modern and Fluid Interface**:

      - TopBar:
         - ✅ **DONE** Displays application name.
         - ✅ **DONE** Filter amphibians by type.
         - ✅ **DONE** Supports dynamic behavior on scroll.
      - Light/Dark Mode:
         - ✅ **DONE** Fully supports Material 3 with adaptive light and dark themes.

   - 🔄 **Real-time status management**:

      - ✅ **DONE** Uses a ViewModel to handle API responses and manage UI state.
      - ✅ **DONE** Implements StateFlow for reactive state updates.

   - 🚀 Performance and responsiveness:
   
      - 🟩 **IN PROGRESS** Implements lazy loading for efficient image handling. 
      - ❌ **NOT IMPLEMENTED** Uses Coil for fast and optimized image fetching.
      - ✅ **DONE** Supports smooth scrolling and responsive display.
      
   - 🛠 Error Handling & User Feedback:

      - 🟩 **IN PROGRESS** Displays appropriate error messages for network failures.
      - 🟩 **IN PROGRESS** Provides loading indicators for better UX.

## 🛠️ **Tech Stack**
   - **Kotlin**: Modern, concise language for Android development.
   - **Jetpack Compose**: Declarative UI toolkit for Android.
   - **Material 3**: Modern, accessible user interface.
   - **StateFlow**: Reactive state management for real-time updates.
   - **ViewModel**: MVVM architecture to separate business logic from user interface.
   - **Retrofit**: Effectuer des appels réseau vers l'API REST.
   - **State Management**: Gestion des états à l'aide de mutableStateOf.
   - **Coil** : Download, buffer, decode and cache images
   
## 🚀 **How to Use**
1. **Launch the App**:
   - Open the app on a Android device or emulator.
2. **Choose to filter by amphibian type or not**:
   - Use the top navigation bar to select a specific amphibian type.
   - Tap on "All Types" to reset the filter and view all amphibians.
3. **Navigate on the amphibians list**:
   - Scroll through the grid of amphibians to explore different species.
4. **Select amphibian**:
   - Tap on an amphibian card to view its detailed information.
5. **Return to home screen**:
   - Tap the back arrow in the top bar to return to the amphibians list.
6. **Enjoy the application**:
   - Explore, learn, and discover fascinating amphibians.

## 📸 **Screenshots**
- **Home screen**:

   ![Home screen](screenshots/home_screen.png)

- **Filtered home screen**:

   ![filtered home screen](screenshots/filtered_home_screen.png)

- **Details screen**:

   ![Details screen](screenshots/details_screen.png)

## 🤝 **Contributions**
Contributions are welcome! Feel free to fork the repository and submit a pull request for new features or bug fixes.✅🟩❌