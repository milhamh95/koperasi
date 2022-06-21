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

    public Long getId() {
        return id;
    }

    public Long getMemberId() {
        return memberId;
    }

    public String getName() {
        return name;
    }

    public TransactionType getType() {
        return type;
    }

    public Integer getTotal() {
        return total;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public Long getLoanId() {
        return loanId;
    }

    public Long getInstallmentId() {
        return installmentId;
    }

    public Long getSavingId() {
        return savingId;
    }
}
