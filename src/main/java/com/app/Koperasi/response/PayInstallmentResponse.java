package com.app.Koperasi.response;

import com.app.Koperasi.entity.LoanStatus;

import java.time.LocalDateTime;

public class PayInstallmentResponse {
    private Long id;

    private Long memberId;

    public PayInstallmentResponse() {
    }

    public PayInstallmentResponse(Long id, Long memberId, Integer totalInstallment, LocalDateTime payInstallmentDate, Integer loanRemainder, LoanStatus loanStatus) {
        this.id = id;
        this.memberId = memberId;
        this.totalInstallment = totalInstallment;
        this.payInstallmentDate = payInstallmentDate;
        this.loanRemainder = loanRemainder;
        this.loanStatus = loanStatus;
    }

    private Integer totalInstallment;

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

    public Integer getTotalInstallment() {
        return totalInstallment;
    }

    public void setTotalInstallment(Integer totalInstallment) {
        this.totalInstallment = totalInstallment;
    }

    public LocalDateTime getPayInstallmentDate() {
        return payInstallmentDate;
    }

    public void setPayInstallmentDate(LocalDateTime payInstallmentDate) {
        this.payInstallmentDate = payInstallmentDate;
    }

    public Integer getLoanRemainder() {
        return loanRemainder;
    }

    public void setLoanRemainder(Integer loanRemainder) {
        this.loanRemainder = loanRemainder;
    }

    public LoanStatus getLoanStatus() {
        return loanStatus;
    }

    public void setLoanStatus(LoanStatus loanStatus) {
        this.loanStatus = loanStatus;
    }

    private LocalDateTime payInstallmentDate;

    private Integer loanRemainder;

    private LoanStatus loanStatus;
}
