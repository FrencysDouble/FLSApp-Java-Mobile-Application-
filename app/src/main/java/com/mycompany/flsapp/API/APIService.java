package com.mycompany.flsapp.API;

import com.mycompany.flsapp.Data.APIData.DataAirports;
import com.mycompany.flsapp.Data.APIData.DataCities;
import com.mycompany.flsapp.Data.APIData.DataCountries;

import java.util.ArrayList;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIService {
    @GET("airports.json")
    Observable<ArrayList<DataAirports>> getAirports(
            @Query("locale") String locale,
            @Query("iata-type[]") String[] type,
            @Query("token") String apiKey
    );

    @GET("ru/countries.json")
    Observable<ArrayList<DataCountries>> getCountries(
            @Query("locale") String locale,
            @Query("token") String apiKey
    );

    @GET("ru/cities.json")
    Observable<ArrayList<DataCities>> getCities(
            @Query("locale") String locale,
            @Query("token") String apiKey
    );
}
