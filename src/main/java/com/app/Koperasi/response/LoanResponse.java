package com.app.Koperasi.response;

import java.time.LocalDate;

public class LoanResponse {
    private Long id;

    private Long memberId;

    private Integer total;

    public LoanResponse() {
    }

    private LocalDate loanDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public LoanResponse(Long memberId, Integer total, LocalDate loanDate, LocalDate tenor) {
        this.memberId = memberId;
        this.total = total;
        this.loanDate = loanDate;
        this.tenor = tenor;
    }
}
