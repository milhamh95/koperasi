package com.app.Koperasi.repository;

import com.app.Koperasi.entity.InstallmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface InstallmentRepository extends JpaRepository<InstallmentEntity, Long> {

    @Query(value = "SELECT * FROM installment i WHERE i.loan_id = :loanId ORDER BY i.id DESC LIMIT 1", nativeQuery = true)
    List<InstallmentEntity> findLatestInstallment(Long loanId);
}
