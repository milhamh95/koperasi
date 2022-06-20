package com.app.Koperasi.entity;

import com.vladmihalcea.hibernate.type.basic.PostgreSQLEnumType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "transaction_detail")
@TypeDef(name = "pgsql_enum", typeClass = PostgreSQLEnumType.class)
public class TransactionDetailEntity {
    @Id
    private Long id;

    @Column(name = "member_id")
    private Long memberId;

    private String name;

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

    public TransactionDetailEntity() {
    }

    public TransactionDetailEntity(Long id, Long memberId, String name, TransactionType type, Integer total, LocalDateTime createdTime, Long loanId, Long installmentId, Long savingId) {
        this.id = id;
        this.memberId = memberId;
        this.name = name;
        this.type = type;
        this.total = total;
        this.createdTime = createdTime;
        this.loanId = loanId;
        this.installmentId = installmentId;
        this.savingId = savingId;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    @Override
    public String toString() {
        return "TransactionDetailEntity{" +
                "id=" + id +
                ", memberId=" + memberId +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", total=" + total +
                ", createdTime=" + createdTime +
                ", loanId=" + loanId +
                ", installmentId=" + installmentId +
                ", savingId=" + savingId +
                '}';
    }
}
