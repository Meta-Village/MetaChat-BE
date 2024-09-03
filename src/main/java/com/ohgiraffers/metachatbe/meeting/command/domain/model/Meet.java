package com.ohgiraffers.metachatbe.meeting.command.domain.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tbl_meet")
public class Meet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "meet_id")
    private long meetId;

}
