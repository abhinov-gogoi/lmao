package com.netcat.meow.Controller;

import com.netcat.meow.Service.MainServices;
import com.netcat.meow.Service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin("*")
public class MainController {

    private final MainServices mainService;
    private final TokenService tokenservice;
    @Autowired
    private HttpServletRequest request;

    public MainController() {
        mainService = MainServices.getInstance();
        tokenservice = TokenService.getInstance();
    }

}