package io.pivotal.model;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class StopInfo {
    String stopId;
    String name;

    @SerializedName("lat")
    double latitude;

    @SerializedName("lon")
    double longitude;

    public StopInfo(String stopId, String name, double latitude, double longitude) {
        this.stopId = stopId;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
