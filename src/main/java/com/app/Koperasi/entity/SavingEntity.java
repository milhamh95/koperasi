package com.app.Koperasi.entity;

import com.vladmihalcea.hibernate.type.basic.PostgreSQLEnumType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name="saving")
@Table(name="saving")
@TypeDef(name = "pgsql_enum", typeClass = PostgreSQLEnumType.class)
public class SavingEntity {
    @Id
    @SequenceGenerator(name = "saving_seq", sequenceName = "saving_seq", allocationSize = 1)

    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "saving_seq")

    private Long id;

    @Column(name = "member_id")
    private Long memberId;

    @Column(name = "transaction_id")
    private Long transactionId;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "type")
    @Type(type = "pgsql_enum")
    private TransactionType type;

    private Integer total;

    @Column(name = "current_saving")
    private Integer currentSaving;

    @Column(name = "created_time")
    private LocalDateTime createdTime;

    public SavingEntity(Long memberId, Long transactionId, TransactionType type, Integer total, Integer currentSaving, LocalDateTime createdTime) {
        this.memberId = memberId;
        this.transactionId = transactionId;
        this.type = type;
        this.total = total;
        this.currentSaving = currentSaving;
        this.createdTime = createdTime;
    }

    public SavingEntity() {
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

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
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

    public Integer getCurrentSaving() {
        return currentSaving;
    }

    public void setCurrentSaving(Integer currentSaving) {
        this.currentSaving = currentSaving;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }
}
