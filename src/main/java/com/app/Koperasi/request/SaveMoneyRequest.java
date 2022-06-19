package com.app.Koperasi.request;

public class SaveMoneyRequest {
    private Long memberId;
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
