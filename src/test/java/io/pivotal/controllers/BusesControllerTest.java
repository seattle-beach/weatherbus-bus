package io.pivotal.controllers;

import io.pivotal.TestUtilities;
import io.pivotal.model.Coordinate;
import io.pivotal.model.StopInfo;
import io.pivotal.service.BusService;
import io.pivotal.service.Departure;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.util.ArrayList;
import java.util.List;

import static net.javacrumbs.jsonunit.spring.JsonUnitResultMatchers.json;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@RunWith(MockitoJUnitRunner.class)
public class BusesControllerTest {
    @Mock
    BusService busService;
    @InjectMocks
    BusesController subject;
    private MockMvc mockMvc;

    @Mock
    HandlerExceptionResolver handlerExceptionResolver;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(subject)
                .setHandlerExceptionResolvers(handlerExceptionResolver)
                .build();
    }

    @Test
    public void testGetDepartures() throws Exception {
        List<Departure> departures = new ArrayList<Departure>() {{
            add(new Departure("31", "CENTRAL MAGNOLIA FREMONT", 1452550769000L, 1452550571000L));
            add(new Departure("855", "Lynnwood", 0, 1452551256000L));
            add(new Departure("32", "SEATTLE CENTER FREMONT", 0, 1452554291000L));
        }};

        when(busService.getDeparturesForStop("35")).thenReturn(departures);
        mockMvc.perform(get("/api/v1/departures?stopId=35")).andExpect(
                json().isEqualTo(TestUtilities.jsonFileToString(
                        "src/test/resources/output/DeparturesCollectionForStopGetResponse.json")));
    }

    @Test(expected = MissingServletRequestParameterException.class)
    public void testGetDeparturesWithOutParams() throws Throwable {
        try {
            mockMvc.perform(get("/api/v1/departures"));
        } finally {
            verifyNoMoreInteractions(busService);
        }
    }

    @Test
    public void testGetStop() throws Exception {
        StopInfo stopInfo = new StopInfo("1_75403", "The name of the stop", 47.6098, -122.3332);

        when(busService.getStopInfo("1_75403")).thenReturn(stopInfo);
        mockMvc.perform(get("/api/v1/stops/1_75403")).andExpect(
                json().isEqualTo(TestUtilities.jsonFileToString(
                        "src/test/resources/output/StopsGetResponse.json")));
    }

    @Test
    public void testGetStopsForCoordinate() throws Exception {
        double latitude = 47.653435;
        double longitude = 122.305641;
        double latitudeSpan = 0.01;
        double longitudeSpan = 0.01;
        List<StopInfo> stops = new ArrayList<StopInfo>() {{
            add(new StopInfo("1_10914", "15th Ave NE & NE Campus Pkwy", 47.656422, -122.312164));
            add(new StopInfo("1_10917", "15th Ave NE & NE 40th St", 47.655048, -122.312195));
        }};
        when(busService.getStopsForCoordinate(new Coordinate(latitude,longitude),latitudeSpan,longitudeSpan))
                .thenReturn(stops);

        mockMvc.perform(get("/api/v1/stops?lat=" + latitude + "&lng=" + longitude
                + "&latSpan=" + latitudeSpan + "&lngSpan=" + longitudeSpan))
                .andExpect(json().isEqualTo(TestUtilities.jsonFileToString(
                        "src/test/resources/output/StopsCollectionForCoordinateGetResponse.json"
                )));
    }
}