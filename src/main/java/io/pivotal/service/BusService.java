package io.pivotal.service;

import io.pivotal.Constants;
import io.pivotal.model.Coordinate;
import io.pivotal.model.StopInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.OkClient;

import java.net.UnknownServiceException;
import java.util.List;

@Component
public class BusService {
    @Autowired
    IOneBusAwayService service;

    public List<Departure> getDeparturesForStop(String stopId) throws UnknownServiceException {
        try {
            ArrivalsAndDeparturesForStopResponse response = service
                    .getDeparturesForStop(stopId);
            return response.getData().getEntry().getDepartures();
        }
        catch (RetrofitError e) {
            e.printStackTrace();
            throw new UnknownServiceException(e.getMessage());
        }
    }

    public StopInfo getStopInfo(String stopId) throws UnknownServiceException {
        try {
            StopResponse response = service
                    .getStopInfo(stopId);
            StopInfo stopInfo = new StopInfo(
                    response.getData().getEntry().getStopId(),
                    response.getData().getEntry().getName(),
                    response.getData().getEntry().getLatitude(),
                    response.getData().getEntry().getLongitude());
            return stopInfo;
        }
        catch (RetrofitError e) {
            e.printStackTrace();
            throw new UnknownServiceException(e.getMessage());
        }
    }

    public List<StopInfo> getStopsForCoordinate(Coordinate coordinate, double latSpan, double lngSpan) throws UnknownServiceException {
        try {
            StopsForLocationResponse response = service
                    .getStopsForLocation(coordinate.getLatitude(), coordinate.getLongitude(),
                            latSpan, lngSpan);
            return response.getData().getStops();
        } catch(RetrofitError e) {
            e.printStackTrace();
            throw new UnknownServiceException(e.getMessage());
        }
    }

    @Bean
    public IOneBusAwayService getBusService() {
            RestAdapter.Builder builder = new RestAdapter.Builder().setEndpoint(Constants.ONEBUSAWAY_ENDPOINT);
            builder.setClient(new OkClient());
            RestAdapter adapter = builder.build();
            return adapter.create(IOneBusAwayService.class);
    }
}
