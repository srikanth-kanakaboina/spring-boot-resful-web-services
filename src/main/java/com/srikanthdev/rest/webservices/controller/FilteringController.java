package com.srikanthdev.rest.webservices.controller;

import com.srikanthdev.rest.webservices.model.SomeBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FilteringController {

    @GetMapping("/filtering")
    public SomeBean getSomeBean(){
        return new SomeBean("value1","value2","value3");
    }

}
