package com.app.Koperasi.entity;

import com.vladmihalcea.hibernate.type.basic.PostgreSQLEnumType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity(name="load")
@Table(name="loan")
@TypeDef(
        name = "pgsql_enum",
        typeClass = PostgreSQLEnumType.class
)
public class LoanEntity {
    @Id
    @SequenceGenerator(
            name = "loan_sequence",
            sequenceName = "loan_sequence",
            allocationSize = 1
    )

    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "loan_sequence"
    )

    private Long id;

    @Column(name="transaction_id")
    private Long transactionId;

    @Column(name="loan_date")
    private LocalDate loanDate;

    public Long getId() {
        return id;
    }

    public LoanEntity() {
    }

    public LoanEntity(Long id, Long transactionId, LocalDate loanDate, Long tenor, LoanStatus status, LocalDateTime createdTime) {
        this.id = id;
        this.transactionId = transactionId;
        this.loanDate = loanDate;
        this.tenor = tenor;
        this.status = status;
        this.createdTime = createdTime;
    }

    public LoanEntity(Long transactionId, LocalDate loanDate, Long tenor, LoanStatus status, LocalDateTime createdTime) {
        this.transactionId = transactionId;
        this.loanDate = loanDate;
        this.tenor = tenor;
        this.status = status;
        this.createdTime = createdTime;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public LocalDate getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(LocalDate loanDate) {
        this.loanDate = loanDate;
    }

    public Long getTenor() {
        return tenor;
    }

    public void setTenor(Long tenor) {
        this.tenor = tenor;
    }

    public LoanStatus getStatus() {
        return status;
    }

    public void setStatus(LoanStatus status) {
        this.status = status;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }

    private Long tenor;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "status")
    @Type(type = "loan_status" )
    private LoanStatus status;

    private LocalDateTime createdTime;
}
