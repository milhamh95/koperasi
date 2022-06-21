package com.app.Koperasi.request;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class WithdrawMoneyRequest {

    @NotNull(message = "total should not be empty")
    @Min(value = 50000, message = "minimum money to withdraw is 50000")
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
