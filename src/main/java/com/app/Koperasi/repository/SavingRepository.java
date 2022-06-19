package com.app.Koperasi.repository;

import com.app.Koperasi.entity.SavingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SavingRepository extends JpaRepository<SavingEntity, Long> {
    @Query(value = "SELECT * FROM saving s WHERE s.member_id = :memberId ORDER BY s.id DESC LIMIT 1", nativeQuery = true)
    List<SavingEntity> findLatestSaving(Long memberId);
}
