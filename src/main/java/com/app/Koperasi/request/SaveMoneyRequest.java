package com.app.Koperasi.request;

public class SaveMoneyRequest {
    private Long memberId;
    private Long total;

    public SaveMoneyRequest(Long memberId, Long total) {
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

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }
}
