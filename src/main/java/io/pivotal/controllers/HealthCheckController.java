package io.pivotal.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HealthCheckController {
    @RequestMapping("/healthcheck")
    public @ResponseBody String index() {
        return "weatherbus-bus ok";
    }
}
