package com.app.Koperasi.controller;

import com.app.Koperasi.response.TransactionDetailResponse;
import com.app.Koperasi.usecase.TransactionUsecase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
public class TransactionController {

    private final TransactionUsecase transactionUsecase;

    @Autowired
    public TransactionController(TransactionUsecase transactionUsecase) {
        this.transactionUsecase = transactionUsecase;
    }

    @GetMapping(path = "/transactions")
    public List<TransactionDetailResponse> fetchTransactions(
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate startDate,
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate endDate
            ) {
        return transactionUsecase.fetchTransactions(startDate, endDate);
    }
}
