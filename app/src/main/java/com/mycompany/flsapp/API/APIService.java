package com.mycompany.flsapp.API;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIService {
    @GET("airports.json")
    Call<DataAirports> getAirports(
            @Query("locale") String locale,
            @Query("types[]") String[] types,
            @Query("token") String apiKey
    );
}
