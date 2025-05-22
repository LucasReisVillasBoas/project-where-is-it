package com.example.project_where_is_it.data.api;

import com.example.project_where_is_it.data.model.Localization;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GeocodingService {
    @GET("json")
    Call<Localization> geocodificarEndereco(
            @Query("address") String endereco,
            @Query("key") String apiKey
    );
}