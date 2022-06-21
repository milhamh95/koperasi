package com.app.Koperasi.request;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class SaveMoneyRequest {
    @NotNull(message = "memberId should not be empty")
    @Min(value = 1, message = "memberId is not valid")
    private Long memberId;

    @NotNull(message = "total should not be empty")
    @Min(value = 50000, message = "minimum money to save is 50000")
    private Integer total;

    public SaveMoneyRequest(Long memberId, Integer total) {
        this.memberId = memberId;
        this.total = total;
    }

    public SaveMoneyRequest() {
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
}
