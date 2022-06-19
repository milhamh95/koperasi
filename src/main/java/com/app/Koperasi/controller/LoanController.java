package com.app.Koperasi.controller;

import com.app.Koperasi.request.ApplyLoanRequest;
import com.app.Koperasi.request.PayInstallmentRequest;
import com.app.Koperasi.response.ApplyLoanResponse;
import com.app.Koperasi.usecase.LoanUsecase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public PayInstallmentRequest payInstallment(@RequestBody PayInstallmentRequest req) {
        loanUsecase.PayInstallment(req);
        return req;
    }
}
