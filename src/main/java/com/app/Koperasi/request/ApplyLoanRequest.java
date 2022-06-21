package com.app.Koperasi.request;


import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class ApplyLoanRequest {
    @NotNull(message = "memberId should not be empty")
    @Min(value = 1, message = "memberId is not valid")
    private Long memberId;

    @NotNull(message = "total should not be empty")
    @Min(value = 50000, message = "minimum apply loan is 50000")
    private Integer total;

    @NotNull(message = "loanDate should not be empty")
    private LocalDate loanDate;

    @NotNull(message = "tenor should not be empty")
    private LocalDate tenor;

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

    public ApplyLoanRequest(Long memberId, Integer total, LocalDate loanDate, LocalDate tenor) {
        this.memberId = memberId;
        this.total = total;
        this.loanDate = loanDate;
        this.tenor = tenor;
    }

    public ApplyLoanRequest() {
    }
}
