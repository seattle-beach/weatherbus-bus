package io.pivotal.view;

import io.pivotal.model.StopInfo;
import lombok.Data;

@Data
public class StopInfoPresenter extends JsonPresenter {
    private final StopInfo data;
}
