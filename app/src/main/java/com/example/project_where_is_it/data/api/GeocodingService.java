package com.example.project_where_is_it.data.api;

import com.example.project_where_is_it.data.model.Localization;
import com.example.project_where_is_it.data.model.LocationIQResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GeocodingService {
    @GET("search")
    Call<List<LocationIQResponse>> geocodeAddress(
            @Query("key") String apiKey,
            @Query("q") String address,
            @Query("format") String format
    );
}