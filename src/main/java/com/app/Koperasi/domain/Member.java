package com.app.Koperasi.domain;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

public class Member {
    private Long id;
    private String name;
    private String address;
    private LocalDateTime createdTime;

    public Member(){}

    public Member(Long id, String name, String address, LocalDateTime createdTime) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.createdTime = createdTime;
    }

    public Member(String name, String address, LocalDateTime createdTime) {
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
