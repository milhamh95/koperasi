package com.app.Koperasi.request;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class PayInstallmentRequest {

    @NotNull(message = "total should not be empty")
    @Min(value = 10000, message = "minimum installment is 10000")
    private Integer total;

    @NotNull(message = "installmentDate should not be empty")
    private LocalDate installmentDate;

    public PayInstallmentRequest(Integer total, LocalDate installmentDate) {
        this.total = total;
        this.installmentDate = installmentDate;
    }

    public PayInstallmentRequest() {
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
