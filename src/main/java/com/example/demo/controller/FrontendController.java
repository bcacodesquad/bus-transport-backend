package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Controller to handle frontend routing.
 * Routes all non-API requests to index.html for React Router handling.
 */
@Controller
public class FrontendController {

    /**
     * Redirect to index.html for all frontend routes
     * This allows React Router to handle client-side routing
     */
    @GetMapping(value = "/{path:^(?!api|h2-console|actuator).*}/**")
    public String redirect(@PathVariable String path) {
        return "forward:/index.html";
    }

    @GetMapping("/")
    public String home() {
        return "forward:/index.html";
    }
}
