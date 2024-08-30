package com.ohgiraffers.metachatbe.summary.command.application.controller;

import com.ohgiraffers.metachatbe.summary.command.application.service.AiCommunicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class AiCommunicationController {
    @Autowired
    private AiCommunicationService myService;


    @GetMapping("/sendGetRequest")
    public Mono<String> sendGetRequest() {
        return myService.sendGetRequest()
                .doOnNext(response -> System.out.println("Response: " + response))
                .doOnError(error -> System.err.println("Error: " + error.getMessage()));
    }

    @GetMapping("/sendPostRequest")
    public Mono<String> sendPostRequest() {
        return myService.sendPostRequest()
                .doOnNext(response -> System.out.println("Response: " + response))
                .doOnError(error -> System.err.println("Error: " + error.getMessage()));
    }
}
