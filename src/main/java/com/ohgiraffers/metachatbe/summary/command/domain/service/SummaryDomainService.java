package com.ohgiraffers.metachatbe.summary.command.domain.service;

import com.ohgiraffers.metachatbe.summary.command.domain.model.Summary;
import com.ohgiraffers.metachatbe.summary.command.domain.repository.SummaryRepository;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class SummaryDomainService {
    SummaryRepository summaryRepository;

    public SummaryDomainService(SummaryRepository summaryRepository) {
        this.summaryRepository = summaryRepository;
    }

    //create
    public void createNewSummary(Summary summary) {
        summaryRepository.save(summary);
    }

    //read
    public Summary findSummary(Long summaryId) {
        return summaryRepository.findById(summaryId).orElseThrow(()->
                new NoSuchElementException("해당하는 요약 id가 없습니다"));
    }

    //update
    @Deprecated
    public void updateSummary(Summary summary) {
        throw new IllegalArgumentException("요약 기록은 변경할 수 없습니다");
    }


    //delete
    public void deleteSummary(Long summaryId) {
        summaryRepository.deleteById(summaryId);
    }
}
