package com.app.Koperasi.repository;

import com.app.Koperasi.entity.InstallmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InstallmentRepository extends JpaRepository<InstallmentEntity, Long> {

//    @Query("SELECT i FROM installment i WHERE i.loanId = ?1 limit 1 order by i.id desc limit 1")
//    @Query(value = "SELECT * FROM installment i WHERE i.loanId = ?1 order by i.id desc limit 1", nativeQuery = true)
//    Optional<InstallmentEntity> findTopByOrderByAgeDesc(Long id);

    Optional<InstallmentEntity> findTopByLoanId(Long id);
}
