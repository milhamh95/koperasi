package com.app.Koperasi.entity;

import com.vladmihalcea.hibernate.type.basic.PostgreSQLEnumType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity(name = "loan")
@Table(name = "loan")
@TypeDef(name = "pgsql_enum", typeClass = PostgreSQLEnumType.class)
public class LoanEntity {
    @Id
    @SequenceGenerator(name = "loan_seq", sequenceName = "loan_seq", allocationSize = 1)

    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "loan_seq")

    private Long id;

    @Column(name = "transaction_id")
    private Long transactionId;

    @Column(name = "member_id")
    private Long memberId;

    private Integer total;

    @Column(name = "loan_date")
    private LocalDate loanDate;

    private LocalDate tenor;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "loan_status")
    @Type(type = "pgsql_enum")
    private LoanStatus status;

    @Column(name= "created_time")
    private LocalDateTime createdTime;

    public LoanEntity() {
    }

    public LoanEntity(Long transactionId, Long memberId, Integer total, LocalDate loanDate, LocalDate tenor, LoanStatus status, LocalDateTime createdTime) {
        this.transactionId = transactionId;
        this.memberId = memberId;
        this.total = total;
        this.loanDate = loanDate;
        this.tenor = tenor;
        this.status = status;
        this.createdTime = createdTime;
    }

    public Long getId() {
        return id;
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

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public LocalDate getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(LocalDate loanDate) {
        this.loanDate = loanDate;
    }

    public LocalDate getTenor() {
        return tenor;
    }

    public void setTenor(LocalDate tenor) {
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
}
