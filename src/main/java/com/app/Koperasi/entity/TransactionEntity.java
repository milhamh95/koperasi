package com.app.Koperasi.entity;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table
public class TransactionEntity {
    @Id
    @SequenceGenerator(
            name = "transaction_sequence",
            sequenceName = "transaction_sequence",
            allocationSize = 1
    )

    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "transaction_sequence"
    )

    private Long id;
    private Long memberId;
    private String name;
    private Integer total;

    @CreationTimestamp
    private LocalDateTime createdTime;

    public TransactionEntity(){}

    public TransactionEntity(Long memberId, String name, Integer total, LocalDateTime createdTime) {
        this.memberId = memberId;
        this.name = name;
        this.total = total;
        this.createdTime = createdTime;
    }

    public TransactionEntity(Long id, Long memberId, String name, Integer total, LocalDateTime createdTime) {
        this.id = id;
        this.memberId = memberId;
        this.name = name;
        this.total = total;
        this.createdTime = createdTime;
    }
}
