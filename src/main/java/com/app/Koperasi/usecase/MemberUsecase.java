package com.app.Koperasi.usecase;

import com.app.Koperasi.domain.Member;
import com.app.Koperasi.entity.MemberEntity;
import com.app.Koperasi.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberUsecase {

    private final MemberRepository memberRepository;

    @Autowired
    public MemberUsecase(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Member addMember(Member member) {
        MemberEntity memberEntity = new MemberEntity(
                member.getName(),
                member.getAddress()
        );
        memberRepository.save(memberEntity);
        return member;
    }

//    public List<Member> getMembers() {
//       return memberRepository.findAll();
//    }
//
//    public Optional<Member> getMember(Long memberId) {
//        return memberRepository.findById(memberId);
//    }
}
