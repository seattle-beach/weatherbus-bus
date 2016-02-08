package io.pivotal.service;

import com.google.gson.Gson;
import io.pivotal.TestUtilities;
import io.pivotal.model.Coordinate;
import io.pivotal.model.StopInfo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.net.UnknownServiceException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BusServiceTest {
    @Mock
    IOneBusAwayService service;

    @InjectMocks
    BusService subject;

    Gson gson = new Gson();

    String badStop = "bogus";

    @Before
    public void setUp() throws Exception {
        when(service.getStopInfo(badStop)).thenReturn(gson.fromJson(
                TestUtilities.fixtureReader("BadResponse"),
                StopResponse.class));

        when(service.getDeparturesForStop(badStop)).thenReturn(gson.fromJson(
                TestUtilities.fixtureReader("BadResponse"),
                DeparturesResponse.class));
    }

    @Test(expected = UnknownServiceException.class)
    public void getDeparturesForStop_shouldThrowUnknownServiceException_forBadStop() throws Exception {
        subject.getDeparturesForStop(badStop);
    }

    @Test(expected = UnknownServiceException.class)
    public void getStopInfo_shouldThrowUnknownServiceException_forBadStop() throws Exception {
        subject.getStopInfo(badStop);
    }

    @Test
    public void testGetDeparturesForStop() throws Exception {
        final String stopId = "12345";

        DeparturesResponse response = gson.fromJson(
                TestUtilities.fixtureReader("DeparturesForStop"),
                DeparturesResponse.class);
        when(service.getDeparturesForStop(stopId)).thenReturn(response);

        List<Departure> expectedDepartures = new ArrayList<Departure>() {{
            add(new Departure("31", "CENTRAL MAGNOLIA FREMONT", 1453317145000L, 1453317145000L));
            add(new Departure("855", "Lynnwood", 0, 1516561850000L));
            add(new Departure("32", "SEATTLE CENTER FREMONT", 1516563660000L, 1516563660000L));
        }};

        assertEquals(expectedDepartures, subject.getDeparturesForStop("12345"));
    }

    @Test
    public void testGetStopInfo() throws Exception {
        final String stopId = "1_75403";

        StopResponse stopResponse = gson.fromJson(
                TestUtilities.fixtureReader("StopInfo"),
                StopResponse.class);
        when(service.getStopInfo(stopId)).thenReturn(stopResponse);

        StopInfo expected = new StopInfo(stopId, "Stevens Way & Benton Ln",47.6098, -122.3332);
        StopInfo stopInfo = subject.getStopInfo(stopId);

        assertEquals(expected, stopInfo);
    }

    @Test
    public void testGetStopsForCoordinate() throws Exception {
        Coordinate coordinate = new Coordinate(47.653435, -122.305641);
        double latSpan=0.01;
        double lngSpan= 0.01;
        StopsForLocationResponse response = gson.fromJson(
                TestUtilities.fixtureReader("StopsForLocation"),
                StopsForLocationResponse.class);
        when(service.getStopsForLocation(coordinate.getLatitude(), coordinate.getLongitude(), latSpan, lngSpan))
                .thenReturn(response);
        List<StopInfo> expected = new ArrayList<StopInfo>() {{
            add(new StopInfo("1_10914", "15th Ave NE & NE Campus Pkwy", 47.656422, -122.312164));
            add(new StopInfo("1_10917", "15th Ave NE & NE 40th St", 47.655048, -122.312195));
        }};
        List<StopInfo> actual = subject.getStopsForCoordinate(coordinate, latSpan, lngSpan);

        assertEquals(expected, actual);
    }
}