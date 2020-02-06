package com.kotor.crud.contollers;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

@RestController
@Slf4j
public class LoggingController {
    private Logger logger = LoggerFactory.getLogger(LoggingController.class);

    @RequestMapping("/**")
    public String index(HttpServletRequest request) {
        System.out.println("I am here!");
        logger.info(request.getRequestURL().toString() + "?" + request.getQueryString());
        return "Logging happens here...";
    }
}
