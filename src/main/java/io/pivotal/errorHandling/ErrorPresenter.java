package io.pivotal.errorHandling;

import io.pivotal.view.JsonPresenter;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ErrorPresenter extends JsonPresenter {
    private final List<ErrorResponse> errors;

    public ErrorPresenter(String message) {
        this.errors = new ArrayList<ErrorResponse>() {{
            add(new ErrorResponse(message));
        }};
    }

    @Data
    public static class ErrorResponse {
        private final String message;

        public ErrorResponse(String message) {
            this.message = message;
        }
    }
}
