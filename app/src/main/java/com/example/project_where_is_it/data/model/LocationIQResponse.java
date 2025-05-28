package com.example.project_where_is_it.data.model;

import com.google.gson.annotations.SerializedName;

public class LocationIQResponse {
    @SerializedName("lat")
    private String lat;

    @SerializedName("lon")
    private String lon;

    public double getLatitude() {
        return Double.parseDouble(lat);
    }

    public double getLongitude() {
        return Double.parseDouble(lon);
    }
}
