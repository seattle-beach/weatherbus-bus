package io.pivotal.errorHandling;

import com.google.gson.JsonSyntaxException;
import io.pivotal.TestUtilities;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import retrofit.RetrofitError;

import static net.javacrumbs.jsonunit.spring.JsonUnitResultMatchers.json;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by pivotal on 1/12/16.
 */
@RunWith(MockitoJUnitRunner.class)
public class ErrorControllerTest {

    @InjectMocks
    ErrorController subject;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(subject).build();
    }

    @Test
    public void testGenericError() throws Exception {
        mockMvc.perform(get(ErrorPathConstants.ERROR_PATH))
                .andExpect(status().isInternalServerError())
                .andExpect(
                        json().isEqualTo(TestUtilities.jsonFileToString("src/test/resources/output/GenericError.json")));
    }

    @Test
    public void testRuntimeError() throws Exception {
        mockMvc.perform(get(ErrorPathConstants.ERROR_BAD_REQUEST))
                .andExpect(status().isBadRequest())
                .andExpect(
                        json().isEqualTo(TestUtilities.jsonFileToString("src/test/resources/output/BadRequestError.json")));
    }
}
