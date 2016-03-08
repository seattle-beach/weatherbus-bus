package io.pivotal.view;

import io.pivotal.model.StopInfo;
import io.pivotal.model.StopReferences;
import lombok.Data;

@Data
public class StopInfoPresenter extends JsonPresenter {
    private final StopInfo data;
    private final StopReferences included;
}
