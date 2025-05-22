package com.example.project_where_is_it.ui.viewmodel;

import com.example.project_where_is_it.data.model.Coordinates;

public interface CoordinatesCallback {
    void onCoordinatesReceived(Coordinates coordinates);
}