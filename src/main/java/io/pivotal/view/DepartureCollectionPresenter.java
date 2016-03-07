package io.pivotal.view;

import io.pivotal.model.Departure;
import lombok.Data;

import java.util.List;

@Data
public class DepartureCollectionPresenter extends JsonPresenter {
    private final List<Departure> data;
}
