package com.ohgiraffers.metachatbe.summary.command.application.controller;

import com.ohgiraffers.metachatbe.meeting.command.application.dto.MeetingDTO;
import com.ohgiraffers.metachatbe.meeting.command.application.service.MeetingService;
import com.ohgiraffers.metachatbe.summary.command.application.dto.SummaryDTO;
import com.ohgiraffers.metachatbe.summary.command.domain.service.SummaryDomainService;
import com.ohgiraffers.metachatbe.util.enumtype.ZoneName;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.NoSuchElementException;


@Controller
@Tag(name = "요약 웹페이지", description = "요약 웹페이지 구조")
public class SummaryWebController {


    private final MeetingService meetingService;
    private final SummaryDomainService summaryDomainService;

    public SummaryWebController(MeetingService meetingService, SummaryDomainService summaryDomainService) {
        this.meetingService = meetingService;
        this.summaryDomainService = summaryDomainService;
    }


    @Operation(summary = "요약 검색", description = "월드와 존 넘버 기준으로 검색합니다")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "페이지를 불러옵니다"),
            @ApiResponse(responseCode = "400", description = "잘못된 요청입니다.")
    })
    // Form 페이지 로드
    @GetMapping("/web-summary/search")
    public String searchSummaryForm() {
        return "summary/find-summary";
    }

    @Operation(summary = "검색 결과 표시", description = "해당 지역에서 발생한 모든 회의를 불러옵니다.<br/><br/>" +
            "검색된 회의 결과가 하나 뿐인 경우, 요약 내용 표시 페이지로 리다이렉트 됩니다.<br/><br/>" +
            "쿼리 파라미터로 바로 검색이 가능합니다.<br/>쿼리 파라미터를 이용할 경우 zone-name 설정에 주의하세요.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "페이지를 불러옵니다"),
            @ApiResponse(responseCode = "400", description = "잘못된 요청입니다.")
    })
    @GetMapping("/web-summary/summary-list")
    public String findSummaryList(@RequestParam("world-id") long worldId,
                                  @RequestParam("zone-name") String zoneName,
                                  Model model) {
        System.out.println("검색을 시작합니다 : " + worldId + " / " + zoneName);

        if (isNOTValidZoneName(zoneName)) {
            model.addAttribute("error",
                    "zone_name 이 유효하지 않습니다 : " + zoneName);
            model.addAttribute("errorCode",
                    HttpStatus.BAD_REQUEST);
            return "errorPage";
        }

        //리스트 검색
        List<MeetingDTO> meetings = meetingService.findMeetingByWorldIdAndZoneName(worldId, ZoneName.valueOf(zoneName));

        if (meetings == null || meetings.isEmpty()) {
            model.addAttribute("error",
                    "해당하는 회의를 찾을 수 없습니다  world-id: " + worldId + " / zone-name: " + zoneName);
            model.addAttribute("errorCode",
                    HttpStatus.BAD_REQUEST);
            return "errorPage";
        }

        if (meetings.size() == 1) {
            return "redirect:/web-summary/summary?meeting-id=" + meetings.getFirst().getMeetingId();
        }

        //결과 출력
        System.out.println(meetings);
        model.addAttribute("meetings", meetings);
        return "summary/summary-list";
    }

    private boolean isNOTValidZoneName(String zoneName) {
        if (zoneName == null) {
            return true;
        }

        try {
            // 문자열을 Enum 값으로 변환 시도
            ZoneName.valueOf(zoneName);
            return false;
        } catch (IllegalArgumentException e) {
            System.out.println("zoneName 값이 유효하지 않습니다.");
            return true;
        }
    }


    @Operation(summary = "요약 내용 표시", description = "검색된 미팅 ID로 요약을 불러옵니다<br/>" +
            "미팅 ID를 알고 있다면 쿼리 파라미터로 바로 입력해서 열람할 수 있습니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "페이지를 불러옵니다"),
            @ApiResponse(responseCode = "400", description = "잘못된 요청입니다."),
            @ApiResponse(responseCode = "500", description = "서버 내부 에러")
    })
    @GetMapping("/web-summary/summary")
    public String findSummary(@RequestParam("meeting-id") long meetingId, Model model) {
        System.out.println("요약을 가져옵니다. : " + meetingId);

        MeetingDTO meeting = meetingService.findMeetingById(meetingId);
        if (meeting == null) {
            model.addAttribute("error",
                    "존재하지 않는 미팅 ID 입니다. : " + meetingId);
            model.addAttribute("errorCode",
                    HttpStatus.BAD_REQUEST);
            return "errorPage";
        }
        model.addAttribute("meeting", meeting);

        try {
            SummaryDTO summary = new SummaryDTO(summaryDomainService.findSummary(meetingId));
            model.addAttribute("summary", summary);
        }catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
            model.addAttribute("error",
                    "존재하지 않는 요약 ID 입니다. : " + meetingId);
            model.addAttribute("errorCode",
                    HttpStatus.INTERNAL_SERVER_ERROR);
            return "errorPage";
        }catch (Exception e) {
            System.out.println(e.getMessage());
            model.addAttribute("error",
                    "예상치 못한 에러 발생  meetingId : " + meetingId);
            model.addAttribute("errorCode",
                    HttpStatus.INTERNAL_SERVER_ERROR);
            return "errorPage";
        }


        return "summary/show-summary";
    }
}
