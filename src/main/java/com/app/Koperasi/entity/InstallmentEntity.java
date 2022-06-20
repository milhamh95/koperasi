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

    @Column(name = "transaction_id")
    private Long transactionId;

    private Integer total;

    @Column(name = "loan_remainder")
    private Integer loanRemainder;

    @Column(name = "created_time")
    private LocalDateTime createdTime;

    public InstallmentEntity() {
    }

    public InstallmentEntity(Long loanId, Long transactionId, Integer total, Integer loanRemainder, LocalDateTime createdTime) {
        this.loanId = loanId;
        this.transactionId = transactionId;
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

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
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
}
