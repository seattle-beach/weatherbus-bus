package io.pivotal.service;

import com.google.gson.Gson;
import io.pivotal.TestUtilities;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.net.UnknownServiceException;

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
}