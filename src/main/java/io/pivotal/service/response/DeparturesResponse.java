package io.pivotal.service.response;

import com.google.gson.annotations.SerializedName;
import io.pivotal.model.Departure;
import lombok.Data;

import java.util.List;

@Data
public class DeparturesResponse {
    @SerializedName("data")
    private final ArrivalsAndDeparturesResponseData data;

    @Data
    public static class ArrivalsAndDeparturesResponseData {
        @SerializedName("entry")
        private final ArrivalsAndDeparturesEntry entry;

        @Data
        public static class ArrivalsAndDeparturesEntry {
            @SerializedName("arrivalsAndDepartures")
            List<Departure> departures;
        }
    }
}
