package com.mycompany.flsapp.API;

import com.mycompany.flsapp.BuildConfig;
import com.mycompany.flsapp.Data.APIData.DataCities;
import com.mycompany.flsapp.Data.APIData.DataCountries;
import com.mycompany.flsapp.Model.DataMerging;

import java.io.IOException;
import java.util.ArrayList;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIManager {
    private static final String BASE_URL = "https://api.travelpayouts.com/data/";

    private APIService apiService;

    public APIManager() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();

        apiService = retrofit.create(APIService.class);
    }

    public APIService getApiService() {
        return apiService;
    }

    public @NonNull Observable<ArrayList<DataMerging.Data>> getCountryAndCitiesData() throws IOException {
        String locale = "ru";
        String apiKey = BuildConfig.API_KEY;

        Observable<ArrayList<DataCountries>> countriesObservable = apiService.getCountries(locale, apiKey).subscribeOn(Schedulers.io());
        Observable<ArrayList<DataCities>> citiesObservable = apiService.getCities(locale, apiKey).subscribeOn(Schedulers.io());

        return Observable.zip(
                countriesObservable,
                citiesObservable,
                DataMerging::mergeData
        ).observeOn(Schedulers.io());
    }
}

