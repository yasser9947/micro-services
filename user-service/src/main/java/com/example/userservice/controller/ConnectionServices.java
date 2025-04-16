package com.example.userservice.controller;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

@RestController
@RequestMapping("/user")
public class ConnectionServices {

    private final DiscoveryClient discoveryClient;
    private final RestClient restClient;

    public ConnectionServices(DiscoveryClient discoveryClient, RestClient.Builder restClientBuilder) {
        this.discoveryClient = discoveryClient;
        restClient = restClientBuilder.build();
    }

    @GetMapping("/helloEureka")
    public String helloWorld() {
        ServiceInstance serviceInstance = discoveryClient.getInstances("product-service").get(0);
       // service layer
        try {
            String name = restClient.get()
                    .uri(serviceInstance.getUri() + "/hello-world")
                    .retrieve()
                    .body(String.class);
// done
            return name + "yahoooo";
        } catch (Exception e) {
            return  "sorry";
        }



    }
}
