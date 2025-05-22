package com.example.project_where_is_it.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ArrayAdapter;

import com.example.project_where_is_it.R;
import com.example.project_where_is_it.data.model.Address;

import java.util.List;

public class AddressAdapter extends ArrayAdapter<Address> {

    public AddressAdapter(Context context, List<Address> addresses) {
        super(context, 0, addresses);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Address address = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.item_address, parent, false);
        }

        TextView tvStreet = convertView.findViewById(R.id.tvStreet);
        TextView tvNeighborhood = convertView.findViewById(R.id.tvNeighborhood);
        TextView tvCityState = convertView.findViewById(R.id.tvCityState);

        if (address != null) {
            tvStreet.setText(address.getLogradouro());
            tvNeighborhood.setText(address.getBairro());
            tvCityState.setText(String.format("%s - %s", address.getLocalidade(), address.getUf()));
        }

        return convertView;
    }
}
