package io.pivotal.service;

import io.pivotal.Config;
import org.springframework.stereotype.Component;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

@Component
public interface IOneBusAwayService {
    @GET("/api/where/arrivals-and-departures-for-stop/{stop}.json?minutesBefore=15&minutesAfter=45")
    DeparturesResponse getDeparturesForStop(@Path("stop") String stopId);

    @GET("/api/where/stop/{stop}.json")
    StopResponse getStopInfo(@Path("stop") String stopId);

    @GET("/api/where/stops-for-location.json")
    StopsForLocationResponse getStopsForLocation(@Query("lat") double lat, @Query("lon") double lng,
                                                 @Query("latSpan") double latSpan, @Query("lonSpan") double lngSpan);
}