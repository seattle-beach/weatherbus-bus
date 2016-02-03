package io.pivotal.view;

import io.pivotal.service.Departure;
import lombok.Data;

import java.util.List;

@Data
public class DepartureCollectionPresenter extends JsonPresenter {
    private final List<Departure> data;
}
