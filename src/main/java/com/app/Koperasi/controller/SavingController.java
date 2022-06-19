package com.app.Koperasi.controller;

import com.app.Koperasi.request.SaveMoneyRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class SavingController {
    @PostMapping(path = "/money")
    public void saveMoney(@RequestBody SaveMoneyRequest req) {

    }
}
