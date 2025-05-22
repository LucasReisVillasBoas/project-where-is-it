package com.example.project_where_is_it.ui.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.project_where_is_it.R;
import com.example.project_where_is_it.data.model.Address;
import com.example.project_where_is_it.ui.viewmodel.LocalizationViewModel;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapFragment extends Fragment implements OnMapReadyCallback {

    private MapView mapView;
    private GoogleMap googleMap;
    private LocalizationViewModel viewModel;

    public MapFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_map, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        mapView = view.findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);
        viewModel = new ViewModelProvider(requireActivity()).get(LocalizationViewModel.class);
    }

    @Override
    public void onMapReady(GoogleMap map) {
        this.googleMap = map;
        Address address = viewModel.getEnderecoSelecionado().getValue();

        if (address != null) {
            viewModel.getCoordinatesForAddress(address, coordinates -> {
                if (coordinates != null) {
                    LatLng location = new LatLng(coordinates.getLatitude(), coordinates.getLongitude());

                    googleMap.addMarker(new MarkerOptions().position(location).title(address.getLogradouro()));
                    googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 15));
                }
            });
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }
}

