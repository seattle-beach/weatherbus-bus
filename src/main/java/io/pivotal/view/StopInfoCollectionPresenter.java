package io.pivotal.view;

import io.pivotal.model.StopInfo;
import lombok.Data;

import java.util.List;

@Data
public class StopInfoCollectionPresenter extends JsonPresenter{
    private List<StopInfo> data;

    public StopInfoCollectionPresenter(List<StopInfo> data) {
        this.data = data;
    }
}
