package com.example.project_where_is_it.ui.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.project_where_is_it.data.api.GeocodingService;
import com.example.project_where_is_it.data.api.IbgeService;
import com.example.project_where_is_it.data.api.ViaCepService;
import com.example.project_where_is_it.data.model.Coordinates;
import com.example.project_where_is_it.data.model.Localization;
import com.example.project_where_is_it.data.model.State;
import com.example.project_where_is_it.data.model.Address;
import com.example.project_where_is_it.data.model.City;
import com.example.project_where_is_it.data.repository.LocalizationRepository;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LocalizationViewModel extends ViewModel {

    private final LocalizationRepository repository;

    public LocalizationViewModel() {
        ViaCepService viaCepService = new Retrofit.Builder().baseUrl("https://viacep.com.br/ws/").addConverterFactory(GsonConverterFactory.create()).build().create(ViaCepService.class);

        IbgeService ibgeService = new Retrofit.Builder().baseUrl("https://servicodados.ibge.gov.br/api/v1/localidades/").addConverterFactory(GsonConverterFactory.create()).build().create(IbgeService.class);
        GeocodingService geocodingService = new Retrofit.Builder().baseUrl("https://maps.googleapis.com/maps/api/geocode/").addConverterFactory(GsonConverterFactory.create()).build().create(GeocodingService.class);

        repository = new LocalizationRepository(viaCepService, ibgeService, geocodingService);

        loadEstados();
    }

    private final MutableLiveData<List<State>> estados = new MutableLiveData<>();
    private final MutableLiveData<List<City>> municipios = new MutableLiveData<>();
    private final MutableLiveData<List<Address>> enderecos = new MutableLiveData<>();

    private final MutableLiveData<Address> enderecoSelecionado = new MutableLiveData<>();

    public LiveData<List<State>> getEstados() {
        return estados;
    }

    public LiveData<List<City>> getMunicipios() {
        return municipios;
    }

    public LiveData<List<Address>> getEnderecos() {
        return enderecos;
    }

    public LiveData<Address> getEnderecoSelecionado() {
        return enderecoSelecionado;
    }

    public void setEnderecoSelecionado(Address endereco) {
        enderecoSelecionado.setValue(endereco);
    }

    public void loadEstados() {
        repository.listState().enqueue(new Callback<List<State>>() {
            @Override
            public void onResponse(Call<List<State>> call, Response<List<State>> response) {
                if (response.isSuccessful()) {
                    List<State> states = response.body();
                    estados.setValue(states);
                    for (State s : states) {
                        Log.d("Localization Repository", "Estado: " + s.getNome()); // exemplo de getNome()
                    }
                } else {
                    try {
                        String errorBody = response.errorBody().string();
                        Log.e("Localization Repository", "Erro na resposta: " + errorBody + " " + response.message() + " " + response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<State>> call, Throwable t) {
                Log.e("Localization Repository", "Falha na chamada: " + t.getMessage());
            }
        });
    }

    public void loadMunicipios(String uf) {
        repository.listCity(uf).enqueue(new Callback<List<City>>() {
            @Override
            public void onResponse(Call<List<City>> call, Response<List<City>> response) {
                if (response.isSuccessful()) {
                    municipios.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<City>> call, Throwable t) {
            }
        });
    }

    public void buscarEnderecos(String uf, String cidade, String logradouro) {
        repository.searchAddresses(uf, cidade, logradouro).enqueue(new Callback<List<Address>>() {
            @Override
            public void onResponse(Call<List<Address>> call, Response<List<Address>> response) {
                if (response.isSuccessful()) {
                    enderecos.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Address>> call, Throwable t) {
            }
        });
    }

    public void getCoordinatesForAddress(Address address, CoordinatesCallback callback) {
        String fullAddress = address.getLogradouro() + ", " + address.getLocalidade() + ", " + address.getUf();

        repository.geocodeAddress(fullAddress).enqueue(new Callback<Localization>() {
            @Override
            public void onResponse(Call<Localization> call, Response<Localization> response) {

                if (response.isSuccessful() && response.body() != null && !response.body().getResults().isEmpty()) {

                    double lat = response.body().getResults().get(0).geometry.location.lat;
                    double lng = response.body().getResults().get(0).geometry.location.lng;

                    callback.onCoordinatesReceived(new Coordinates(lat, lng));
                } else {
                    callback.onCoordinatesReceived(null);
                }
            }

            @Override
            public void onFailure(Call<Localization> call, Throwable t) {
                callback.onCoordinatesReceived(null);
            }
        });
    }

}
