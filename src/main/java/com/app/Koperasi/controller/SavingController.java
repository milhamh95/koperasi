package com.app.Koperasi.controller;

import com.app.Koperasi.request.SaveMoneyRequest;
import com.app.Koperasi.response.SaveMoneyResponse;
import com.app.Koperasi.usecase.SavingUsecase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SavingController {

    private final SavingUsecase savingUsecase;

    @Autowired
    public SavingController(SavingUsecase savingUsecase) {
        this.savingUsecase = savingUsecase;
    }

    @PostMapping(path = "/saving")
    public SaveMoneyResponse saveMoney(@RequestBody SaveMoneyRequest req) {
        return savingUsecase.saveMoney(req);
    }
}
