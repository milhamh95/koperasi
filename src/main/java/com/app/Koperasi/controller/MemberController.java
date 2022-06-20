package com.app.Koperasi.controller;

import com.app.Koperasi.request.AddMemberRequest;
import com.app.Koperasi.response.AddMemberResponse;
import com.app.Koperasi.response.MemberDetailResponse;
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
    public List<AddMemberResponse> getMembers() {
        return memberUsecase.getMembers();
    }

    @PostMapping
    public AddMemberResponse addMember(@RequestBody AddMemberRequest member) {
        return memberUsecase.addMember(member);
    }

    @GetMapping(path = "{memberId}")
    public MemberDetailResponse getMember(@PathVariable("memberId") Long memberId) {
        return memberUsecase.getMember(memberId);
    }
}
