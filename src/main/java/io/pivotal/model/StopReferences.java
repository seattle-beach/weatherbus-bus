package io.pivotal.model;

import lombok.Data;

import java.util.List;

@Data
public class StopReferences {

    private final List<RouteReference> routes;

    @Data
    public static class RouteReference {
        private final String id;
        private final String shortName;
        private final String longName;
    }

}
