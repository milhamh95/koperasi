package com.app.Koperasi.response;

import com.app.Koperasi.entity.TransactionType;

import java.time.LocalDateTime;

public class TransactionDetailResponse {
    private Long id;

    private Long memberId;

    private String name;

    private TransactionType type;

    private Integer total;

    private Long refId;

    private LocalDateTime createdTime;

    public TransactionDetailResponse() {
    }

    public TransactionDetailResponse(Long id, Long memberId, String name, TransactionType type, Integer total, LocalDateTime createdTime) {
        this.id = id;
        this.memberId = memberId;
        this.name = name;
        this.type = type;
        this.total = total;
        this.createdTime = createdTime;
    }

    public Long getId() {
        return id;
    }

    public Long getMemberId() {
        return memberId;
    }

    public String getName() {
        return name;
    }

    public TransactionType getType() {
        return type;
    }

    public Integer getTotal() {
        return total;
    }

    public Long getRefId() {
        return refId;
    }

    public void setRefId(Long refId) {
        this.refId = refId;
    }
}
