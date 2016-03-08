package io.pivotal.controllers;

import io.pivotal.TestUtilities;
import io.pivotal.model.Coordinate;
import io.pivotal.model.Departure;
import io.pivotal.model.StopInfo;
import io.pivotal.model.StopReferences;
import io.pivotal.service.BusService;
import io.pivotal.service.response.StopResponse;
import io.pivotal.service.response.StopsForLocationResponse;
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
        when(busService.getStopData("1_75403")).thenReturn(
                new StopResponse.StopData(
                        new StopInfo("1_75403", "The name of the stop", 47.6098, -122.3332, "S", new ArrayList<String>() {{
                            add("1_100140");
                            add("29_880");
                        }}),
                        new StopReferences(new ArrayList<StopReferences.RouteReference>() {{
                            add(new StopReferences.RouteReference("1_100140", "25"));
                            add(new StopReferences.RouteReference("29_880", "880"));
                        }})));
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
            add(new StopInfo("1_10914", "15th Ave NE & NE Campus Pkwy", 47.656422, -122.312164, "S", new ArrayList<String>() {{
                add("1_100223");
                add("40_100451");
                add("40_586");
            }}));
            add(new StopInfo("1_10917", "15th Ave NE & NE 40th St", 47.655048, -122.312195, "S", new ArrayList<String>() {{
                add("1_100140");
                add("1_100223");
                add("40_586");
            }}));
        }};

        StopReferences references = new StopReferences(new ArrayList<StopReferences.RouteReference>() {{
            add(new StopReferences.RouteReference("1_100223", "43"));
            add(new StopReferences.RouteReference("1_100140", "25"));
            add(new StopReferences.RouteReference("40_100451", "556"));
            add(new StopReferences.RouteReference("40_586", "586"));
        }});

        StopsForLocationResponse.StopsData response = new StopsForLocationResponse.StopsData(stops, references);

        when(busService.getStopsForCoordinate(new Coordinate(latitude,longitude),latitudeSpan,longitudeSpan))
                .thenReturn(response);

        mockMvc.perform(get("/api/v1/stops?lat=" + latitude + "&lng=" + longitude
                + "&latSpan=" + latitudeSpan + "&lngSpan=" + longitudeSpan))
                .andExpect(json().isEqualTo(TestUtilities.jsonFileToString(
                        "src/test/resources/output/StopsCollectionForCoordinateGetResponse.json"
                )));
    }
}