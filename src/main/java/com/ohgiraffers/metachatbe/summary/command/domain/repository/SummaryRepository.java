package com.ohgiraffers.metachatbe.summary.command.domain.repository;

import com.ohgiraffers.metachatbe.summary.command.domain.model.Summary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SummaryRepository extends JpaRepository<Summary, Long> {

}
