package com.app.Koperasi.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "member")
@Table(name = "member")
public class MemberEntity {
    @Id
    @SequenceGenerator(name = "member_seq", sequenceName = "member_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "member_seq")
    private Long id;
    private String name;
    private String address;

    @Column(name= "created_time")
    private LocalDateTime createdTime;

    public MemberEntity() {
    }

    public MemberEntity(String name, String address, LocalDateTime createdTime) {
        this.name = name;
        this.address = address;
        this.createdTime = createdTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }
}
