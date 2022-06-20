package com.app.Koperasi.response;

import java.time.LocalDateTime;
import java.util.List;

public class MemberDetailResponse {
    private Long id;

    private String name;

    private String address;

    private LocalDateTime createdTime;

    private List<MemberTransactionDetailResponse> transactions;

    public MemberDetailResponse() {
    }

    public MemberDetailResponse(Long id, String name, String address, LocalDateTime createdTime, List<MemberTransactionDetailResponse> transactions) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.createdTime = createdTime;
        this.transactions = transactions;
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

    public List<MemberTransactionDetailResponse> getTransactions() {
        return transactions;
    }
}
