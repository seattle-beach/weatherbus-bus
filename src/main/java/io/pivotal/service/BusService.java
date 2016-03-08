package io.pivotal.service;

import io.pivotal.model.Coordinate;
import io.pivotal.model.Departure;
import io.pivotal.service.response.DeparturesResponse;
import io.pivotal.service.response.StopResponse;
import io.pivotal.service.response.StopsForLocationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import retrofit.RetrofitError;

import java.net.UnknownServiceException;
import java.util.List;

@Component
public class BusService {
    @Autowired
    IOneBusAwayService service;

    public List<Departure> getDeparturesForStop(String stopId) throws UnknownServiceException {
        try {
            DeparturesResponse response = service
                    .getDeparturesForStop(stopId);
            if (response.getData() == null) {
                throw new UnknownServiceException();
            }
            return response.getData().getEntry().getDepartures();
        } catch (RetrofitError e) {
            e.printStackTrace();
            throw new UnknownServiceException(e.getMessage());
        }
    }

    public StopResponse.StopData getStopData(String stopId) throws UnknownServiceException {
        try {
            StopResponse response = service
                    .getStopInfo(stopId);
            if (response.getData() == null) {
                throw new UnknownServiceException();
            }
            return response.getData();
        } catch (RetrofitError e) {
            e.printStackTrace();
            throw new UnknownServiceException(e.getMessage());
        }
    }

    public StopsForLocationResponse.StopsData getStopsForCoordinate(Coordinate coordinate, double latSpan, double lngSpan) throws UnknownServiceException {
        try {
            StopsForLocationResponse response = service
                    .getStopsForLocation(coordinate.getLatitude(), coordinate.getLongitude(),
                            latSpan, lngSpan);
            return response.getData();
        } catch (RetrofitError e) {
            e.printStackTrace();
            throw new UnknownServiceException(e.getMessage());
        }
    }
}
