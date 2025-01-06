package com.bmcotuk.emr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/external")
public class ExternalApiController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/patients")
    public Object fetchExternalPatientData() {
        String apiUrl = "https://api.example.com/patients";
        return restTemplate.getForObject(apiUrl, Object.class);
    }
}