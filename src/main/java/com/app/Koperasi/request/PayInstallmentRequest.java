package com.app.Koperasi.request;

public class PayInstallmentRequest {
    private Long memberId;
    private Integer total;

    public PayInstallmentRequest(Long memberId, Integer total) {
        this.memberId = memberId;
        this.total = total;
    }

    public Long getMemberId() {
        return memberId;
    }

    public PayInstallmentRequest() {
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
