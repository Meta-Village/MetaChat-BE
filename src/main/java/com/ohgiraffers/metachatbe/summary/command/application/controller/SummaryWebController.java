package com.ohgiraffers.metachatbe.summary.command.application.controller;

import com.ohgiraffers.metachatbe.meeting.command.application.dto.MeetingDTO;
import com.ohgiraffers.metachatbe.meeting.command.application.service.MeetingService;
import com.ohgiraffers.metachatbe.summary.command.application.dto.SummaryDTO;
import com.ohgiraffers.metachatbe.summary.command.domain.service.SummaryDomainService;
import com.ohgiraffers.metachatbe.util.enumtype.ZoneName;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.NoSuchElementException;

@Controller
public class SummaryWebController {


    private final MeetingService meetingService;
    private final SummaryDomainService summaryDomainService;

    public SummaryWebController(MeetingService meetingService, SummaryDomainService summaryDomainService) {
        this.meetingService = meetingService;
        this.summaryDomainService = summaryDomainService;
    }


    // Form 페이지 로드
    @GetMapping("/web-summary/search")
    public String searchSummaryForm() {
        return "summary/find-summary";
    }

    // Form 처리 및 결과 페이지로 이동
    @GetMapping("/web-summary/summary-list")
    public String findSummaryList(@RequestParam("world_id") long worldId,
                                  @RequestParam("zone_name") String zoneName,
                                  Model model) {
        System.out.println("검색을 시작합니다 : " + worldId + " / " + zoneName);

        if (isNOTValidZoneName(zoneName)) {
            model.addAttribute("error",
                    "zone_name 이 유효하지 않습니다 : " + zoneName);
            return "errorPage";
        }

        //리스트 검색
        List<MeetingDTO> meetings = meetingService.findMeetingByWorldIdAndZoneName(worldId, ZoneName.valueOf(zoneName));

        if (meetings == null || meetings.isEmpty()) {
            model.addAttribute("error",
                    "No meetings found for world_id: " + worldId + " and zone_name: " + zoneName);
            return "errorPage";
        }

        if (meetings.size() == 1) {
            return "redirect:/web-summary/summary?meetingId=" + meetings.getFirst().getMeetingId();
        }

        //결과 출력
        System.out.println(meetings);
        model.addAttribute("meetings", meetings);
        return "summary/summaryList";
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

    @GetMapping("/web-summary/summary")
    public String findSummary(@RequestParam long meetingId, Model model) {
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
