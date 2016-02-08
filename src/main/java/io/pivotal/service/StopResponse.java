package io.pivotal.service;

import com.google.gson.annotations.SerializedName;
import io.pivotal.model.Coordinate;
import lombok.Data;

@Data
public class StopResponse {
    public Coordinate getCoordinates() {
        StopData.StopEntry entry = getData().getEntry();
        return new Coordinate(entry.latitude, entry.longitude);
    }

    public String getName() {
        return data.getEntry().getName();
    }

    @SerializedName("data")
    StopData data;

    @Data
    public static class StopData {
        @SerializedName("entry")
        StopEntry entry;

        @Data
        public static class StopEntry {
            @SerializedName("lat")
            double latitude;

            @SerializedName("lon")
            double longitude;

            @SerializedName("name")
            String name;

            @SerializedName("id")
            String stopId;

            String direction;
        }
    }
}



