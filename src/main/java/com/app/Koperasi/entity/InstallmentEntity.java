package com.app.Koperasi.entity;

import javax.persistence.*;
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

    public InstallmentEntity(Long loanId, Integer total, Integer loanRemainder, LocalDateTime createdTime) {
        this.loanId = loanId;
        this.total = total;
        this.loanRemainder = loanRemainder;
        this.createdTime = createdTime;
    }

    public InstallmentEntity(Long id, Long loanId, Integer total, Integer loanRemainder, LocalDateTime createdTime) {
        this.id = id;
        this.loanId = loanId;
        this.total = total;
        this.loanRemainder = loanRemainder;
        this.createdTime = createdTime;
    }

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

    public Integer getLoanRemainder() {
        return loanRemainder;
    }

    public void setLoanRemainder(Integer loanRemainder) {
        this.loanRemainder = loanRemainder;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }

    @Column(name = "loan_remainder")
    private Integer loanRemainder;

    @Column(name = "created_time")
    private LocalDateTime createdTime;
}
