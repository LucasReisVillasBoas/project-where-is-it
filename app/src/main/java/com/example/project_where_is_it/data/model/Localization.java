package com.example.project_where_is_it.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Localization {

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public class Geometry {
        @SerializedName("location")
        public Location location;

        public Location getLocation() {
            return location;
        }
    }

    public class Location {
        @SerializedName("lat")
        public double lat;
        @SerializedName("lng")
        public double lng;

        public double getLat() {
            return lat;
        }

        public double getLng() {
            return lng;
        }
    }

    public class Result {
        @SerializedName("geometry")
        public Geometry geometry;

        public Geometry getGeometry() {
            return geometry;
        }
    }

    @SerializedName("results")
    public List<Result> results;
    @SerializedName("status")
    public String status;

    public double getLatitude() {
        return results != null && !results.isEmpty() ? results.get(0).geometry.location.lat : 0;
    }

    public double getLongitude() {
        return results != null && !results.isEmpty() ? results.get(0).geometry.location.lng : 0;
    }
}
