package com.example.project_where_is_it.data.repository;

import android.net.Uri;
import android.util.Log;

import com.example.project_where_is_it.data.api.ViaCepService;
import com.example.project_where_is_it.data.api.IbgeService;
import com.example.project_where_is_it.data.api.GeocodingService;
import com.example.project_where_is_it.data.model.*;

import java.util.List;

import retrofit2.Call;

public class LocalizationRepository {
    private final ViaCepService viaCepService;
    private final IbgeService ibgeService;
    private final GeocodingService geocodingService;

    public LocalizationRepository(ViaCepService viaCep, IbgeService ibge, GeocodingService geo) {
        this.viaCepService = viaCep;
        this.ibgeService = ibge;
        this.geocodingService = geo;
    }

    public Call<List<Address>> searchAddresses(String uf, String cidade, String logradouro) {
        return viaCepService.searchAddress(uf, cidade, logradouro);
    }

    public Call<List<State>> listState() {
        Call<List<State>> result = ibgeService.listarEstados();
        return result;
    }

    public Call<List<City>> listCity(String uf) {
        return ibgeService.listarMunicipios(uf);
    }

    public Call<List<LocationIQResponse>> geocodeAddress(String fullAddress) {
        return geocodingService.geocodeAddress("pk.9d9fef656ba32e58481a803d13c52cd1", fullAddress, "json");
    }

}