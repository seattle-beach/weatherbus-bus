package io.pivotal.errorHandling;

import lombok.Getter;

//TODO: Consider making this a table
public enum ErrorMessages {
    UNKNOWN(ErrorPathConstants.ERROR_PATH, "Unknown error occurred"),
    BAD_REQUEST(ErrorPathConstants.ERROR_BAD_REQUEST, "Unexpected response from One Bus Away"),
    ;
    @Getter
    private final String errorPath;
    @Getter
    private final String errorMessage;

    ErrorMessages(String errorPath, String errorMessage) {
        this.errorPath = errorPath;
        this.errorMessage = errorMessage;
    }
}
