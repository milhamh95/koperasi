package com.app.Koperasi.entity;

import com.vladmihalcea.hibernate.type.basic.PostgreSQLEnumType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "member_transaction_detail")
@TypeDef(name = "pgsql_enum", typeClass = PostgreSQLEnumType.class)
public class MemberTransactionDetailEntity {
    @Id
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "transaction_type")
    @Type(type = "pgsql_enum")
    private TransactionType type;

    private Integer total;

    @Column(name = "created_time")
    private LocalDateTime createdTime;

    @Column(name = "loan_id")
    private Long loanId;

    @Column(name = "installment_id")
    private Long installmentId;

    @Column(name = "saving_id")
    private Long savingId;

    public MemberTransactionDetailEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }

    public Long getLoanId() {
        return loanId;
    }

    public void setLoanId(Long loanId) {
        this.loanId = loanId;
    }

    public Long getInstallmentId() {
        return installmentId;
    }

    public void setInstallmentId(Long installmentId) {
        this.installmentId = installmentId;
    }

    public Long getSavingId() {
        return savingId;
    }

    public void setSavingId(Long savingId) {
        this.savingId = savingId;
    }
}
