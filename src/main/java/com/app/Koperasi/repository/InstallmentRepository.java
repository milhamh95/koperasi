package com.app.Koperasi.repository;

import com.app.Koperasi.entity.InstallmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstallmentRepository extends JpaRepository<InstallmentEntity, Long> {
}
