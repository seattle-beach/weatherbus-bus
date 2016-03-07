package io.pivotal.service.response;

import io.pivotal.model.StopInfo;
import io.pivotal.model.StopReferences;
import lombok.Data;

@Data
public class StopResponse {
    public String getName() {
        return data.getEntry().getName();
    }

    private final StopData data;

    @Data
    public static class StopData {
        private final StopInfo entry;
        private final StopReferences references;
    }
}



