package io.pivotal.service;

import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Response;
import io.pivotal.Config;
import io.pivotal.model.Coordinate;
import io.pivotal.model.StopInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.OkClient;

import java.io.IOException;
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

    public StopInfo getStopInfo(String stopId) throws UnknownServiceException {
        try {
            StopResponse response = service
                    .getStopInfo(stopId);
            if (response.getData() == null) {
                throw new UnknownServiceException();
            }
            StopInfo stopInfo = new StopInfo(
                    response.getData().getEntry().getStopId(),
                    response.getData().getEntry().getName(),
                    response.getData().getEntry().getLatitude(),
                    response.getData().getEntry().getLongitude());
            return stopInfo;
        } catch (RetrofitError e) {
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
        } catch (RetrofitError e) {
            e.printStackTrace();
            throw new UnknownServiceException(e.getMessage());
        }
    }

    @Bean
    public IOneBusAwayService getBusService() {
        RestAdapter.Builder builder = new RestAdapter.Builder().setEndpoint(Config.ONEBUSAWAY_ENDPOINT);
        builder.setClient(new OkClient());
        builder.setRequestInterceptor(request -> request.addQueryParam("key", Config.ONEBUSAWAY_KEY));
        RestAdapter adapter = builder.build();
        return adapter.create(IOneBusAwayService.class);
    }
}
