package com.srikanthdev.rest.webservices.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonVersioningController {

    @GetMapping("v1/person")
    public PersonV1 persionv1(){
        return new PersonV1("Srikanth");
    }

    @GetMapping("v2/person")
    public PersonV2 persionv2(){
        return new PersonV2(new Name("Srikanth","Kanakaboina"));
    }
}
