package io.pivotal.model;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.List;

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

    List<String> routeIds;

    public StopInfo(String stopId, String name, double latitude, double longitude, String direction, List<String> routeIds) {
        this.stopId = stopId;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.direction = direction;
        this.routeIds = routeIds;
    }
}
