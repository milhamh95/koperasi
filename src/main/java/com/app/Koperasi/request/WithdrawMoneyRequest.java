package com.app.Koperasi.request;

public class WithdrawMoneyRequest {
    private Integer total;

    public WithdrawMoneyRequest(Integer total) {
        this.total = total;
    }

    public WithdrawMoneyRequest() {
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
}
