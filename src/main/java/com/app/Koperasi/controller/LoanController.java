package com.app.Koperasi.controller;

import com.app.Koperasi.request.ApplyLoanRequest;
import com.app.Koperasi.request.PayInstallmentRequest;
import com.app.Koperasi.response.ApplyLoanResponse;
import com.app.Koperasi.response.PayInstallmentResponse;
import com.app.Koperasi.usecase.LoanUsecase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class LoanController {
    private final LoanUsecase loanUsecase;

    @Autowired
    public LoanController(LoanUsecase loanUsecase) {
        this.loanUsecase = loanUsecase;
    }

    @PostMapping(path = "/loans")
    public ApplyLoanResponse applyLoad(@RequestBody ApplyLoanRequest req) {
        if (req.getTenor().equals(req.getLoanDate())) {
            throw new ResponseStatusException(
                    HttpStatus.OK, "tenor can't be equal with loan date"
            );
        }

        if (req.getTenor().isBefore(req.getLoanDate())) {
            throw new ResponseStatusException(
                    HttpStatus.OK, "tenor can't be before loan date"
            );
        }

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
