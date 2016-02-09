package io.pivotal.config;

import io.pivotal.service.IOneBusAwayService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import retrofit.RestAdapter;
import retrofit.client.OkClient;

@Configuration
public class BusConfig {
    public static final String ONEBUSAWAY_ENDPOINT = System.getenv("ONEBUSAWAY_ENDPOINT");
    public static final String ONEBUSAWAY_KEY = System.getenv("ONEBUSAWAY_KEY");

    @Bean
    public IOneBusAwayService getBusService() {
        RestAdapter.Builder builder = new RestAdapter.Builder().setEndpoint(ONEBUSAWAY_ENDPOINT);
        builder.setClient(new OkClient());
        builder.setRequestInterceptor(request -> request.addQueryParam("key", ONEBUSAWAY_KEY));
        RestAdapter adapter = builder.build();
        return adapter.create(IOneBusAwayService.class);
    }
}
