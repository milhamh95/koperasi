package com.app.Koperasi.usecase;

import com.app.Koperasi.domain.Member;
import com.app.Koperasi.entity.MemberEntity;
import com.app.Koperasi.repository.MemberRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public List<Member> getMembers() {
       List <MemberEntity> memberEntities = memberRepository.findAll();

       List <Member> members = new ArrayList<>();

       ModelMapper modelMapper = new ModelMapper();

       for (int i = 0; i < memberEntities.size(); i++) {
            Member member = modelMapper.map(memberEntities.get(i), Member.class);
            members.add(member);
        }

       return members;


    }

    public Member getMember(Long memberId) {
        Optional <MemberEntity> memberEntity = memberRepository.findById(memberId);

        Member member = new Member(
                memberEntity.get().getId(),
                memberEntity.get().getName(),
                memberEntity.get().getAddress(),
                memberEntity.get().getCreatedTime());

        return member;
    }
}
