package com.app.Koperasi.usecase;

import com.app.Koperasi.entity.MemberEntity;
import com.app.Koperasi.entity.MemberTransactionDetailEntity;
import com.app.Koperasi.repository.MemberRepository;
import com.app.Koperasi.repository.MemberTransactionDetailRepository;
import com.app.Koperasi.request.AddMemberRequest;
import com.app.Koperasi.response.AddMemberResponse;
import com.app.Koperasi.response.MemberDetailResponse;
import com.app.Koperasi.response.MemberTransactionDetailResponse;
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

    private final MemberTransactionDetailRepository memberTransactionDetailRepository;

    @Autowired
    public MemberUsecase(MemberRepository memberRepository, MemberTransactionDetailRepository memberTransactionDetailRepository ) {
        this.memberRepository = memberRepository;
        this.memberTransactionDetailRepository = memberTransactionDetailRepository;
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

       for (MemberEntity memberData: memberEntities) {
            AddMemberResponse member = new AddMemberResponse(
                    memberData.getId(),
                    memberData.getName(),
                    memberData.getAddress(),
                    memberData.getCreatedTime()
            );
            members.add(member);
        }

       return members;
    }

    public MemberDetailResponse getMember(Long memberId) {
        MemberEntity memberEntity = memberRepository.findById(memberId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "member is not found")
        );

        List<MemberTransactionDetailEntity> memberTrxs = memberTransactionDetailRepository.findMemberTransactionDetail(memberId);

        List<MemberTransactionDetailResponse> trxs = new ArrayList<>();

        for (MemberTransactionDetailEntity trxDetail : memberTrxs) {
            MemberTransactionDetailResponse trx = new MemberTransactionDetailResponse(
                    trxDetail.getId(),
                    trxDetail.getType(),
                    trxDetail.getTotal(),
                    trxDetail.getCreatedTime()
            );

            trx.setRefId(
                    trxDetail.getLoanId(),
                    trxDetail.getInstallmentId(),
                    trxDetail.getSavingId());

            trxs.add(trx);
        }

        return new MemberDetailResponse(
                memberEntity.getId(),
                memberEntity.getName(),
                memberEntity.getAddress(),
                memberEntity.getCreatedTime(),
                trxs
        );
    }
}
