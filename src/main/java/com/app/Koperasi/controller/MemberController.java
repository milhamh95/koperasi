package com.app.Koperasi.controller;

import com.app.Koperasi.domain.Member;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/members")
public class MemberController {
    @PostMapping
    public Member addMember(@RequestBody Member member) {
        return member;
    }
}
