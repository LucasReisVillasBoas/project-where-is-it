package com.example.project_where_is_it.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.project_where_is_it.R;
import com.example.project_where_is_it.data.model.Address;
import com.example.project_where_is_it.ui.adapter.AddressAdapter;
import com.example.project_where_is_it.ui.viewmodel.LocalizationViewModel;

import java.util.List;

public class ResultsFragment extends Fragment {

    private LocalizationViewModel viewModel;
    private ListView listView;

    public ResultsFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_results, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        listView = view.findViewById(R.id.addressListView);
        viewModel = new ViewModelProvider(requireActivity()).get(LocalizationViewModel.class);

        viewModel.getEnderecos().observe(getViewLifecycleOwner(), this::populateAddressList);

        listView.setOnItemClickListener((parent, itemView, position, id) -> {
            Address selectedAddress = (Address) parent.getItemAtPosition(position);
            viewModel.setEnderecoSelecionado(selectedAddress);

            requireActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, new MapFragment()) // !!!!!
                    .addToBackStack(null)
                    .commit();
        });
    }

    private void populateAddressList(List<Address> addresses) {
        AddressAdapter adapter = new AddressAdapter(requireContext(), addresses);
        listView.setAdapter(adapter);
    }
}
