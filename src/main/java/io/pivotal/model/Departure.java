package io.pivotal.model;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class Departure {
    @SerializedName("routeShortName")
    private final String routeShortName;

    @SerializedName("tripHeadsign")
    private final String headsign;

    @SerializedName("predictedDepartureTime")
    private final long predictedTime;

    @SerializedName("scheduledDepartureTime")
    private final long scheduledTime;

}
