package com.app.Koperasi.response;

import com.app.Koperasi.entity.LoanStatus;

import java.time.LocalDateTime;

public class PayInstallmentResponse {
    private Long id;

    private Long memberId;

    public PayInstallmentResponse() {
    }

    private Integer totalInstallment;

    private LocalDateTime payInstallmentDate;

    private Integer loanRemainder;

    private LoanStatus loanStatus;

    public PayInstallmentResponse(Long id, Long memberId, Integer totalInstallment, LocalDateTime payInstallmentDate, Integer loanRemainder, LoanStatus loanStatus) {
        this.id = id;
        this.memberId = memberId;
        this.totalInstallment = totalInstallment;
        this.payInstallmentDate = payInstallmentDate;
        this.loanRemainder = loanRemainder;
        this.loanStatus = loanStatus;
    }

    public Long getId() {
        return id;
    }

    public Long getMemberId() {
        return memberId;
    }

    public Integer getTotalInstallment() {
        return totalInstallment;
    }

    public LocalDateTime getPayInstallmentDate() {
        return payInstallmentDate;
    }

    public Integer getLoanRemainder() {
        return loanRemainder;
    }

    public LoanStatus getLoanStatus() {
        return loanStatus;
    }
}
