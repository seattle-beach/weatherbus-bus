package io.pivotal.service.response;

import io.pivotal.model.StopInfo;
import io.pivotal.model.StopReferences;
import lombok.Data;

import java.util.List;

@Data
public class StopsForLocationResponse {
    private final StopsData data;

    @Data
    static public class StopsData {
        private final List<StopInfo> list;
        private final StopReferences references;
    }
}
