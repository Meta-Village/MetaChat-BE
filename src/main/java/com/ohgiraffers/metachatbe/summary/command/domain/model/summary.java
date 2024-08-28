package com.ohgiraffers.metachatbe.summary.command.domain.model;

import jakarta.persistence.*;

@Entity(name = "tbl_summary")
public class summary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "summary_code")
    private int id;

    @Column(name = "summary")
    private String summary;
}
