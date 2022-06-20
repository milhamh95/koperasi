package com.app.Koperasi.response;

import com.app.Koperasi.entity.TransactionType;

import java.time.LocalDateTime;

public class MemberTransactionDetailResponse {
    private Long id;

    private TransactionType type;

    private Integer total;

    private Long refId;

    private LocalDateTime createdTime;

    public MemberTransactionDetailResponse() {
    }

    public MemberTransactionDetailResponse(Long id, TransactionType type, Integer total, LocalDateTime createdTime) {
        this.id = id;
        this.type = type;
        this.total = total;
        this.createdTime = createdTime;
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

    public Long getRefId() {
        return refId;
    }

    public void setRefId(Long loanId, Long installmentId, Long savingId) {
        switch (this.type) {
            case LOAN:
                this.refId = loanId;
                return;
            case INSTALLMENT:
                this.refId = installmentId;
                return;
            case SAVE:
                this.refId = savingId;
                return;
            case WITHDRAW:
                this.refId = savingId;
        }
    }

}
