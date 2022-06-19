package com.app.Koperasi.controller;

import com.app.Koperasi.request.ApplyLoanRequest;
import com.app.Koperasi.request.PayInstallmentRequest;
import com.app.Koperasi.response.ApplyLoanResponse;
import com.app.Koperasi.response.PayInstallmentResponse;
import com.app.Koperasi.usecase.LoanUsecase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoanController {

    private final LoanUsecase loanUsecase;

    @Autowired
    public LoanController(LoanUsecase loanUsecase) {
        this.loanUsecase = loanUsecase;
    }

    @PostMapping(path = "/loans")
    public ApplyLoanResponse applyLoad(@RequestBody ApplyLoanRequest req) {
        ApplyLoanResponse applyLoanResponse = loanUsecase.applyLoan(req);
        return applyLoanResponse;
    }

    @PostMapping(path = "/loans/{loanId}/installment")
    public PayInstallmentResponse payInstallment(
            @PathVariable("loanId") Long loanId,
            @RequestBody PayInstallmentRequest req) {
        PayInstallmentResponse resp = loanUsecase.PayInstallment(req, loanId);
        return resp;
    }
}
