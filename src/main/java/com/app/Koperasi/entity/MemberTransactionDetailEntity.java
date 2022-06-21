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
