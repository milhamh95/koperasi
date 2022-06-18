package com.app.Koperasi.controller;

import com.app.Koperasi.domain.Member;
import com.app.Koperasi.usecase.MemberUsecase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/members")
public class MemberController {
    private final MemberUsecase memberUsecase;

    @Autowired
    public MemberController(MemberUsecase memberUsecase) {
        this.memberUsecase = memberUsecase;
    }

    @GetMapping
    public List<Member> getMembers() {
        return memberUsecase.getMembers();
    }

    @PostMapping
    public Member addMember(@RequestBody Member member) {
        return memberUsecase.addMember(member);
    }
}
