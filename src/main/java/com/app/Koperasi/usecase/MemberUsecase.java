package com.app.Koperasi.usecase;

import com.app.Koperasi.domain.Member;
import com.app.Koperasi.entity.MemberEntity;
import com.app.Koperasi.repository.MemberRepository;
import com.app.Koperasi.request.AddMemberRequest;
import com.app.Koperasi.response.AddMemberResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class MemberUsecase {

    private final MemberRepository memberRepository;

    @Autowired
    public MemberUsecase(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public AddMemberResponse addMember(AddMemberRequest member) {
        LocalDateTime createdTime = LocalDateTime.now();
        MemberEntity memberEntity = new MemberEntity(
                member.getName(),
                member.getAddress(),
                createdTime
        );
        MemberEntity resMemberEntity = memberRepository.save(memberEntity);
        return new AddMemberResponse(
                resMemberEntity.getId(),
                resMemberEntity.getName(),
                resMemberEntity.getAddress(),
                createdTime
        );
    }

    public List<AddMemberResponse> getMembers() {
       List <MemberEntity> memberEntities = memberRepository.findAll();

       List <AddMemberResponse> members = new ArrayList<>();

       for (int i = 0; i < memberEntities.size(); i++) {
            AddMemberResponse member = new AddMemberResponse(
                    memberEntities.get(i).getId(),
                    memberEntities.get(i).getName(),
                    memberEntities.get(i).getAddress(),
                    memberEntities.get(i).getCreatedTime()
            );
            members.add(member);
        }

       return members;
    }

    public Member getMember(Long memberId) {
        MemberEntity memberEntity = memberRepository.findById(memberId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "member is not found")
        );

        return new Member(
                memberEntity.getId(),
                memberEntity.getName(),
                memberEntity.getAddress(),
                memberEntity.getCreatedTime());
    }
}
