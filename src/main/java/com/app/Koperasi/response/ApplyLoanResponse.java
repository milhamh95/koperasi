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

    public LoanStatus getStatus() {
        return status;
    }

    public void setStatus(LoanStatus status) {
        this.status = status;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }

}
