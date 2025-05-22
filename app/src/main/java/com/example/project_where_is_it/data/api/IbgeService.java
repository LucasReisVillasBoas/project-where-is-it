package com.example.project_where_is_it.data.api;

import com.example.project_where_is_it.data.model.State;
import com.example.project_where_is_it.data.model.City;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface IbgeService {
    @GET("estados")
    Call<List<State>> listarEstados();

    @GET("estados/{uf}/municipios")
    Call<List<City>> listarMunicipios(@Path("uf") String uf);
}
