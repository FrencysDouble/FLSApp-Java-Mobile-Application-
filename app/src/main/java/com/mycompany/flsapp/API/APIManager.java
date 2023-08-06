package com.mycompany.flsapp.API;

import android.util.Log;

import com.mycompany.flsapp.BuildConfig;
import com.mycompany.flsapp.Interfaces.DataCallback;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIManager {
    private static final String BASE_URL = "https://api.travelpayouts.com/data/";

    private APIService apiService;

    public APIManager() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(APIService.class);
    }

    public APIService getApiService() {
        return apiService;
    }

    /*public void GetData(DataCallback callback) {
        String locale = "ru";
        String[] types = new String[]{"airport"};
        String apiKey = BuildConfig.API_KEY;
        Call<List<DataAirports>> call = apiService.getAirports(locale, types, apiKey);
        call.enqueue(new Callback<List<DataAirports>>() {
            @Override
            public void onResponse(Call<List<DataAirports>> call, Response<List<DataAirports>> response) {
                if (response.isSuccessful()) {
                    List<DataAirports> dataAirportsList = response.body();
                    Log.d("APIManager", "Received data: " + dataAirportsList.toString());
                    callback.onAirportDataRecieved(dataAirportsList);
                } else {
                    Log.e("APIManager", "API call failed with code: " + response.code());
                }

            }

            @Override
            public void onFailure(Call<List<DataAirports>> call, Throwable t) {
                callback.onAirportDataFailed(new Exception("Api Error"));

            }
        });

    }*/


    public void GetCountryAndCitiesData(DataCallback callback) {
        String locale = "ru";
        String apiKey = BuildConfig.API_KEY;

        // Запрос данных о странах
        Call<List<DataCountries>> callCountries = apiService.getCountries(locale, apiKey);
        callCountries.enqueue(new Callback<List<DataCountries>>() {
            @Override
            public void onResponse(Call<List<DataCountries>> call, Response<List<DataCountries>> response) {
                if (response.isSuccessful()) {
                    List<DataCountries> dataCountriesList = response.body();
                    Log.d("APIManager", "Received data: Country" + dataCountriesList.toString());

                    Call<List<DataCities>> callCities = apiService.getCities(locale, apiKey);
                    callCities.enqueue(new Callback<List<DataCities>>() {
                        @Override
                        public void onResponse(Call<List<DataCities>> call, Response<List<DataCities>> response) {
                            if (response.isSuccessful()) {
                                List<DataCities> dataCities = response.body();
                                Log.d("APIManager", "Received data: City" + dataCities.toString());

                                Thread thread = new Thread(new Runnable() {
                                    @Override
                                    public void run() {
                                        DataMerging dataMerging = new DataMerging();
                                        dataMerging.CountriesSet(dataCountriesList);
                                        dataMerging.CitiesSet(dataCities);

                                        dataMerging.Merging(callback);
                                    }
                                });
                                thread.start();
                            }
                        }

                        @Override
                        public void onFailure(Call<List<DataCities>> call, Throwable t) {

                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<List<DataCountries>> call, Throwable t) {
            }
        });
    }
}

