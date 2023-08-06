package com.mycompany.flsapp.API;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIService {
    @GET("airports.json")
    Call<List<DataAirports>> getAirports(
            @Query("locale") String locale,
            @Query("iata-type[]") String[] type,
            @Query("token") String apiKey
    );

    @GET("ru/countries.json")
    Call<List<DataCountries>> getCountries(
            @Query("locale") String locale,
            @Query("token") String apiKey
    );

    @GET("ru/cities.json")
    Call<List<DataCities>> getCities(
            @Query("locale") String locale,
            @Query("token") String apiKey
    );
}
