package com.ohgiraffers.metachatbe.summary.command.application.controller;

import com.ohgiraffers.metachatbe.meeting.command.application.dto.MeetingDTO;
import com.ohgiraffers.metachatbe.meeting.command.application.service.MeetingService;
import com.ohgiraffers.metachatbe.util.enumtype.ZoneName;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class SummaryWebController {


    private final MeetingService meetingService;

    public SummaryWebController(MeetingService meetingService) {
        this.meetingService = meetingService;
    }


    // Form 페이지 로드
    @GetMapping("/zone-form")
    public String showZoneForm() {
        return "zoneForm";
    }

    // Form 처리 및 결과 페이지로 이동
    @PostMapping("/summary")
    public String getZones(@RequestParam("world_id") long worldId,
                           @RequestParam("zone_name") String zoneName,
                           Model model) {
        if (isValidZoneName(zoneName)) {
            return null;
        }

        List<MeetingDTO> meetings = meetingService.findMeetingByWorldIdAndZoneName(worldId, ZoneName.valueOf(zoneName));


        model.addAttribute("zones", meetings);
        return "zoneList";  // 결과를 표시할 템플릿으로 이동
    }

    public boolean isValidZoneName(String zoneName) {
        if (zoneName == null) {
            return false;
        }

        try {
            // 문자열을 Enum 값으로 변환 시도
            ZoneName.valueOf(zoneName);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
