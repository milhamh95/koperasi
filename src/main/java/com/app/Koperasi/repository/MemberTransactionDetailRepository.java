package com.app.Koperasi.repository;

import com.app.Koperasi.entity.MemberTransactionDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MemberTransactionDetailRepository extends JpaRepository<MemberTransactionDetailEntity, Long> {
    @Query(value = """
            select t.*, l.id as loan_id, i.id as installment_id, s.id as saving_id
            from transaction t
            left join member m on t.member_id = m.id
            left join loan l on t.id = l.transaction_id
            left join installment i on t.id = i.transaction_id
            left join saving s on s.transaction_id = t.id
            where m.id = :memberId
            order by t.id asc""", nativeQuery = true)
    List<MemberTransactionDetailEntity> findMemberTransactionDetail(Long memberId);
}
