package com.app.Koperasi.response;

import java.time.LocalDateTime;

public class AddMemberResponse {
    private Long id;
    private String name;
    private String address;
    private LocalDateTime createdTime;

    public AddMemberResponse(Long id, String name, String address, LocalDateTime createdTime) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.createdTime = createdTime;
    }

    public AddMemberResponse() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }
}
