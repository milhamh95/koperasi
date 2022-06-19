package com.app.Koperasi.response;

import com.app.Koperasi.entity.LoanStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ApplyLoanResponse {
    private Long id;

    private Long memberId;

    private Integer total;

    private LocalDate loanDate;

    private LocalDate tenor;

    private LoanStatus status;

    private LocalDateTime createdTime;

    public ApplyLoanResponse() {
    }

    public ApplyLoanResponse(Long id, Long memberId, Integer total, LocalDate loanDate, LocalDate tenor, LoanStatus status, LocalDateTime createdTime) {
        this.id = id;
        this.memberId = memberId;
        this.total = total;
        this.loanDate = loanDate;
        this.tenor = tenor;
        this.status = status;
        this.createdTime = createdTime;
    }
}
