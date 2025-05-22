package com.example.project_where_is_it.domain.usecase;

import com.example.project_where_is_it.data.model.Address;
import com.example.project_where_is_it.data.repository.LocalizationRepository;

import java.util.List;

import retrofit2.Call;

public class SearchAddressUseCase {

    private final LocalizationRepository repository;

    public SearchAddressUseCase(LocalizationRepository repository) {
        this.repository = repository;
    }

    public Call<List<Address>> execute(String state, String city, String street) {
        return repository.searchAddresses(state, city, street);
    }
}