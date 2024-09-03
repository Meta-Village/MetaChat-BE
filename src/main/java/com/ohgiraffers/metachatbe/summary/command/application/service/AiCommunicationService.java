package com.ohgiraffers.metachatbe.summary.command.application.service;

import com.ohgiraffers.metachatbe.summary.command.domain.model.AiCommunicationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class AiCommunicationService {


    @Autowired
    private WebClient webClient;

    public Mono<String> sendGetRequest() {
        String url = "https://httpbin.org"; // 요청을 보낼 URL

        return webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(String.class); // 응답을 문자열로 받음
    }

    public Mono<String> sendPostRequest() {
        String url = "https://eagle-prepared-octopus.ngrok-free.app/chatbot_test/chat"; // 원격 서버 URL
        AiCommunicationDTO requestData = new AiCommunicationDTO("안녕하세요"); // 요청에 보낼 데이터
        System.out.println(requestData);

        return webClient.post()
                .uri(url)
//                .header("Content-Type", "application/json")
                .body(BodyInserters.fromValue(requestData)) // 요청 데이터 설정
//                .body(Mono.just(requestData), AiCommunicationDTO.class)
                .retrieve()
                .bodyToMono(String.class); // 응답을 문자열로 받음
    }
}
