package io.pivotal.view;

import io.pivotal.model.StopInfo;
import io.pivotal.model.StopReferences;
import lombok.Data;

import java.util.List;

@Data
public class StopInfoCollectionPresenter extends JsonPresenter{
    private List<StopInfo> data;
    private StopReferences included;

    public StopInfoCollectionPresenter(List<StopInfo> data, StopReferences included) {
        this.data = data;
        this.included = included;
    }
}
