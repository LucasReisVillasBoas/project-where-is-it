package com.example.project_where_is_it.ui.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import com.example.project_where_is_it.R;
import com.example.project_where_is_it.data.model.State;
import com.example.project_where_is_it.data.model.City;
import com.example.project_where_is_it.ui.viewmodel.LocalizationViewModel;

import java.util.List;

public class SearchFragment extends Fragment {

    private LocalizationViewModel viewModel;
    private Spinner stateSpinner, citySpinner;
    private EditText referenceInput;

    public SearchFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        stateSpinner = view.findViewById(R.id.spinnerState);
        citySpinner = view.findViewById(R.id.spinnerCity);
        referenceInput = view.findViewById(R.id.editTextReference);
        Button searchButton = view.findViewById(R.id.buttonSearch);

        viewModel = new ViewModelProvider(requireActivity()).get(LocalizationViewModel.class);

        viewModel.getEstados().observe(getViewLifecycleOwner(), this::populateStateSpinner);
        viewModel.getMunicipios().observe(getViewLifecycleOwner(), this::populateCitySpinner);

        viewModel.loadEstados();

        stateSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                State selectedState = (State) parent.getItemAtPosition(position);
                if (selectedState != null) {
                    viewModel.loadMunicipios(selectedState.getId());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // opcional
            }
        });


        searchButton.setOnClickListener(v -> {
            String uf = ((State) stateSpinner.getSelectedItem()).getSigla();
            String city = ((City) citySpinner.getSelectedItem()).getNome();
            String reference = referenceInput.getText().toString();
            viewModel.buscarEnderecos(uf, city, reference);
            FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();

            fragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, new ResultsFragment())
                    .addToBackStack(null)
                    .commit();
        });
    }

    private void populateStateSpinner(List<State> states) {
        ArrayAdapter<State> adapter = new ArrayAdapter<>(requireContext(),
                android.R.layout.simple_spinner_item, states);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        stateSpinner.setAdapter(adapter);
    }

    private void populateCitySpinner(List<City> cities) {
        ArrayAdapter<City> adapter = new ArrayAdapter<>(requireContext(),
                android.R.layout.simple_spinner_item, cities);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        citySpinner.setAdapter(adapter);
    }
}
