package com.app.Koperasi.usecase;

import com.app.Koperasi.domain.Member;
import com.app.Koperasi.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberUsecase {

    private final MemberRepository memberRepository;

    @Autowired
    public MemberUsecase(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Member addMember(Member member) {
        memberRepository.save(member);
        return member;
    }
}
