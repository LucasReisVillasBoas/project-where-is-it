package com.example.project_where_is_it.data.api;

import com.example.project_where_is_it.data.model.Address;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ViaCepService {
    @GET("{uf}/{cidade}/{logradouro}/json/")
    Call<List<Address>> searchAddress(
            @Path("uf") String uf,
            @Path("cidade") String cidade,
            @Path("logradouro") String logradouro
    );
}
