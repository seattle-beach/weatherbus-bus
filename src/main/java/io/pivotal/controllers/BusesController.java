package io.pivotal.controllers;

import io.pivotal.model.Coordinate;
import io.pivotal.model.StopInfo;
import io.pivotal.service.BusService;
import io.pivotal.service.Departure;
import io.pivotal.view.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/api/v1")
@Controller
public class BusesController {
    @Autowired
    private BusService busService;

    @RequestMapping(path = "departures", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public @ResponseBody String getDepartures(@RequestParam String stopId) throws Exception {
        List<Departure> departures = busService.getDeparturesForStop(stopId);
        return new DepartureCollectionPresenter(departures).toJson();
    }

    @RequestMapping(path = "stops/{stopId}", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public @ResponseBody String getStop(@PathVariable String stopId) throws Exception {
        return new StopInfoPresenter(busService.getStopInfo(stopId)).toJson();
    }

    @RequestMapping(path = "stops", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public @ResponseBody String getStopsForCoordinate(@RequestParam double lat, @RequestParam double lng,
                                              @RequestParam double latSpan, @RequestParam double lngSpan) throws Exception {
        List<StopInfo> stops = busService.getStopsForCoordinate(new Coordinate(lat,lng), latSpan, lngSpan);
        StopInfoCollectionPresenter pres = new StopInfoCollectionPresenter(stops);
        return pres.toJson();
    }
}
