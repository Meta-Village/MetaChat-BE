package com.ohgiraffers.metachatbe.summary.command.application.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ohgiraffers.metachatbe.summary.command.application.dto.AiCommunicationDTO;
import com.ohgiraffers.metachatbe.summary.command.application.dto.SummaryDTO;
import com.ohgiraffers.metachatbe.summary.command.domain.model.Summary;
import com.ohgiraffers.metachatbe.summary.command.domain.service.SummaryDomainService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.concurrent.CompletableFuture;

@Service
public class AiCommunicationService {


    private final WebClient webClient;

    private final SummaryDomainService summaryDomainService;

    public AiCommunicationService(WebClient webClient, SummaryDomainService summaryDomainService) {
        this.webClient = webClient;
        this.summaryDomainService = summaryDomainService;
    }

    public Mono<String> sendGetRequest() {
        String url = "https://httpbin.org"; // 요청을 보낼 URL

        return webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(String.class); // 응답을 문자열로 받음
    }

    public Mono<String> sendPostRequest() {
        String url = "https://eagle-prepared-octopus.ngrok-free.app/stt/voice"; // 원격 서버 URL
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

    /**
     * 움직이면 안됨 아직
     * */
    @Async
    public CompletableFuture<Mono<SummaryDTO>> sendVoiceToAI(SummaryDTO requestData) {
        String url = "https://eagle-prepared-octopus.ngrok-free.app/stt/voice"; // 원격 서버 URL
        Mono<SummaryDTO> summarised =
                webClient.post()
                        .uri(url)
                        .body(Mono.just(requestData), SummaryDTO.class)
                        .retrieve()
                        .bodyToMono(SummaryDTO.class)
                        .flatMap(response ->{
                            saveSummary(response);
                            return Mono.just(response);
                        });
        return CompletableFuture.completedFuture(summarised);
    }

    @Transactional
    public void saveSummary(SummaryDTO summaryDTO) {
        summaryDomainService.createNewSummary(new Summary(
                summaryDTO.getMeetingId(),
                summaryDTO.getMessages()
        ));

    }

    @Async
    @Transactional
    public void saveSummary(String responseData){

        System.out.println("Received Data: " + responseData);

        // responseData를 SummaryDTO로 변환
        ObjectMapper objectMapper = new ObjectMapper();
        SummaryDTO summaryDTO = null;
        try {
            summaryDTO = objectMapper.readValue(responseData, SummaryDTO.class);
            saveSummary(summaryDTO);
        } catch (JsonProcessingException e) {
            System.err.println(e.getMessage());
        }
    }

    @Transactional
    public SummaryDTO findSummary(Long id) {
        return new SummaryDTO(summaryDomainService.findSummary(id));
    }
}
