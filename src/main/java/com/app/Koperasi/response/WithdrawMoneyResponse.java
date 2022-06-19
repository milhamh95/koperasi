package com.app.Koperasi.response;

import java.time.LocalDateTime;

public class WithdrawMoneyResponse {
    public Long id;

    private Long memberId;

    private Integer total;

    private Integer currentSaving;

    private LocalDateTime createdTime;

    public WithdrawMoneyResponse(Long id, Long memberId, Integer total, Integer currentSaving, LocalDateTime createdTime) {
        this.id = id;
        this.memberId = memberId;
        this.total = total;
        this.currentSaving = currentSaving;
        this.createdTime = createdTime;
    }

    public Long getId() {
        return id;
    }

    public Long getMemberId() {
        return memberId;
    }

    public Integer getTotal() {
        return total;
    }

    public Integer getCurrentSaving() {
        return currentSaving;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }
}
