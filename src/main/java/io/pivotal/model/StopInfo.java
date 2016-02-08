package io.pivotal.model;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class StopInfo {
    @SerializedName("id")
    String stopId;
    String name;

    @SerializedName("lat")
    double latitude;

    @SerializedName("lon")
    double longitude;

    String direction;

    public StopInfo(String stopId, String name, double latitude, double longitude, String direction) {
        this.stopId = stopId;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.direction = direction;
    }
}
