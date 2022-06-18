package com.app.Koperasi.controller;

import com.app.Koperasi.request.ApplyLoanRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/loans")
public class LoanController {
    @PostMapping
    public ApplyLoanRequest applyLoad(@RequestBody ApplyLoanRequest req) {
        return req;
    }
}
