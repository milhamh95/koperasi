package com.app.Koperasi.entity;

import com.vladmihalcea.hibernate.type.basic.PostgreSQLEnumType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "transaction")
@Table(name = "transaction")
@TypeDef(name = "pgsql_enum", typeClass = PostgreSQLEnumType.class)
public class TransactionEntity {
    @Id
    @SequenceGenerator(name = "transaction_seq", sequenceName = "transaction_seq", allocationSize = 1)

    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transaction_seq")

    private Long id;
    private Long memberId;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "transaction_type")
    @Type(type = "pgsql_enum")
    private TransactionType type;

    private Integer total;

    private LocalDateTime createdTime;

    public TransactionEntity(Long id, Long memberId, TransactionType type, Integer total, LocalDateTime createdTime) {
        this.id = id;
        this.memberId = memberId;
        this.type = type;
        this.total = total;
        this.createdTime = createdTime;
    }

    public TransactionEntity(Long memberId, TransactionType type, Integer total, LocalDateTime createdTime) {
        this.memberId = memberId;
        this.type = type;
        this.total = total;
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

    public TransactionEntity() {
    }

}
