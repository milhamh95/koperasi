package com.app.Koperasi.request;

import java.time.LocalDate;

public class ApplyLoanRequest {
    private Integer memberId;

    private Integer total;

    private LocalDate loanDate;

    public ApplyLoanRequest(Integer memberId, Integer total, LocalDate loanDate, LocalDate tenor) {
        this.memberId = memberId;
        this.total = total;
        this.loanDate = loanDate;
        this.tenor = tenor;
    }

    public ApplyLoanRequest() {
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public LocalDate getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(LocalDate loanDate) {
        this.loanDate = loanDate;
    }

    public LocalDate getTenor() {
        return tenor;
    }

    public void setTenor(LocalDate tenor) {
        this.tenor = tenor;
    }

    private LocalDate tenor;
}
