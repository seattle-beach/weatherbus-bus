package io.pivotal.errorHandling;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.net.UnknownServiceException;

@ControllerAdvice
public class ErrorController implements org.springframework.boot.autoconfigure.web.ErrorController {
    @Override
    public String getErrorPath() {
        return ErrorPathConstants.ERROR_PATH;
    }

    private static final Logger logger = LoggerFactory.getLogger(ErrorController.class);

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @RequestMapping(ErrorPathConstants.ERROR_PATH)
    public
    @ResponseBody
    String unknownError() {
        return new ErrorPresenter(ErrorMessages.UNKNOWN.getErrorMessage()).toJson();
    }

    @ExceptionHandler({UnknownServiceException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @RequestMapping(ErrorPathConstants.ERROR_BAD_REQUEST)
    public
    @ResponseBody
    String errorNoParams() {
        return new ErrorPresenter(ErrorMessages.BAD_REQUEST.getErrorMessage()).toJson();
    }

}
