# 📍 Where Is It

**Where Is It** is an Android application that allows users to search for addresses using the **ViaCEP** API, filter by state and city via **IBGE APIs**, and view the results on a map using the **Google Geocoding API**.

---

## 🚀 Features

- 🌐 **Search by location**  
  Users can search for addresses by selecting a state and city from spinners and entering a reference (e.g., street name).

- 📋 **ListView for results**  
  The results are displayed in a custom ListView showing basic address information.

- 🗺️ **Map integration**  
  Upon selecting a result, the app fetches the latitude and longitude via the Google Geocoding API and displays the location on a map (Google Maps).

- 📚 **Data source APIs**  
  - [ViaCEP](https://viacep.com.br/) – for address search  
  - [IBGE](https://servicodados.ibge.gov.br/) – for Brazilian states and cities  
  - [Google Geocoding API](https://developers.google.com/maps/documentation/geocoding) – for geolocation

---

## 🧭 App Structure

The app uses a **Navigation Drawer** for easy navigation between screens:

- **Search**: Input fields for state, city, and reference
- **Results**: Displays a custom list of matching addresses
- **Map**: Displays the selected address location on a Google Map

A menu option is also available on the main screen to allow the user to exit the app.

---

## 📱 Technologies Used

- Java
- Android SDK
- Google Maps SDK
- Retrofit or Volley (for API requests)
- Navigation Drawer
- Fragments and ViewModels (MVVM)
- Custom ListView
- Spinner components

---

## 🔧 How It Works

1. **Select a State and City**  
   Data is loaded dynamically from the IBGE API.

2. **Enter a Street or Reference**  
   The user inputs a street name or any identifier.

3. **Fetch and Display Results**  
   Results from ViaCEP are shown in a ListView.

4. **View Location on Map**  
   On tapping a result, its coordinates are fetched using the Google Geocoding API and displayed on a Google Map.

---

## 📌 Requirements

- Android Studio
- Google Maps API Key
- Internet connection

---

## 💡 Usage Example

1. Open the app and go to **Search**
2. Select:  
   - State: `SP`  
   - City: `Presidente Prudente`  
   - Reference: `Avenida Brasil`
3. Click **Search**
4. View results under the **Results** section
5. Tap an address to view its location in the **Map**

---

## 🔐 API Keys

To use the Google Maps and Geocoding services, add your API key in the `AndroidManifest.xml`:

```xml
<meta-data
    android:name="com.google.android.geo.API_KEY"
    android:value="YOUR_API_KEY_HERE"/>
