package com.app.Koperasi.request;

import java.time.LocalDate;

public class PayInstallmentRequest {
    private Integer total;

    private LocalDate installmentDate;

    public PayInstallmentRequest(Integer total, LocalDate installmentDate) {
        this.total = total;
        this.installmentDate = installmentDate;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public LocalDate getInstallmentDate() {
        return installmentDate;
    }

    public void setInstallmentDate(LocalDate installmentDate) {
        this.installmentDate = installmentDate;
    }
}
