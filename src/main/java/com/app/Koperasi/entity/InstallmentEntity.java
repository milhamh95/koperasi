package com.app.Koperasi.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity(name="installment")
@Table(name="installment")
public class InstallmentEntity {
    @Id
    @SequenceGenerator(name = "installment_seq", sequenceName = "installment_seq", allocationSize = 1)

    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "installment_seq")

    private Long id;

    @Column(name = "loan_id")
    private Long loanId;

    private Integer total;

    public InstallmentEntity() {
    }

    public InstallmentEntity(Long loanId, Integer total, Integer loan_remainder, LocalDateTime createdTime) {
        this.loanId = loanId;
        this.total = total;
        this.loan_remainder = loan_remainder;
        this.createdTime = createdTime;
    }

    private Integer loan_remainder;

    public InstallmentEntity(Long id, Long loanId, Integer total, Integer loan_remainder, LocalDateTime createdTime) {
        this.id = id;
        this.loanId = loanId;
        this.total = total;
        this.loan_remainder = loan_remainder;
        this.createdTime = createdTime;
    }

    private LocalDateTime createdTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getLoanId() {
        return loanId;
    }

    public void setLoanId(Long loanId) {
        this.loanId = loanId;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getLoan_remainder() {
        return loan_remainder;
    }

    public void setLoan_remainder(Integer loan_remainder) {
        this.loan_remainder = loan_remainder;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }
}
