package com.app.Koperasi.response;

import java.time.LocalDateTime;

public class SaveMoneyResponse {
    public Long id;

    private Long memberId;

    private Integer total;

    private Integer currentSaving;

    private LocalDateTime createdTime;

    public SaveMoneyResponse(Long id, Long memberId, Integer total, Integer currentSaving, LocalDateTime createdTime) {
        this.id = id;
        this.memberId = memberId;
        this.total = total;
        this.currentSaving = currentSaving;
        this.createdTime = createdTime;
    }
}
