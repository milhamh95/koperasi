package com.app.Koperasi.repository;

import com.app.Koperasi.entity.MemberEntity;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface MemberRepository extends JpaRepository<MemberEntity, Long> {
}
