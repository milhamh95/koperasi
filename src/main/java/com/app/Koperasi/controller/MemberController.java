package com.app.Koperasi.controller;

import com.app.Koperasi.domain.Member;
import com.app.Koperasi.usecase.MemberUsecase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/members")
public class MemberController {
    private final MemberUsecase memberUsecase;

    @Autowired
    public MemberController(MemberUsecase memberUsecase) {
        this.memberUsecase = memberUsecase;
    }

    @PostMapping
    public Member addMember(@RequestBody Member member) {
        return memberUsecase.addMember(member);
    }
}
