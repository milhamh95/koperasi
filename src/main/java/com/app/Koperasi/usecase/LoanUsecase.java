package com.app.Koperasi.usecase;

import com.app.Koperasi.entity.LoanEntity;
import com.app.Koperasi.entity.LoanStatus;
import com.app.Koperasi.entity.TransactionEntity;
import com.app.Koperasi.entity.TransactionType;
import com.app.Koperasi.repository.LoanRepository;
import com.app.Koperasi.repository.TransactionRepository;
import com.app.Koperasi.request.ApplyLoanRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Service
public class LoanUsecase {

    private final TransactionRepository transactionRepository;
    private final LoanRepository loanRepository;

    @Autowired
    public LoanUsecase(TransactionRepository transactionRepository, LoanRepository loanRepository) {
        this.transactionRepository = transactionRepository;
        this.loanRepository = loanRepository;
    }

    @Transactional
    public void applyLoan(ApplyLoanRequest req) {
        LocalDateTime createdTime = LocalDateTime.now();
        TransactionEntity trxEntity = new TransactionEntity(
                req.getMemberId(),
                TransactionType.LOAN,
                req.getTotal(),
                createdTime
        );

        TransactionEntity resTrxEntity = transactionRepository.save(trxEntity);

        LoanEntity loanEntity = new LoanEntity(
            resTrxEntity.getId(),
                req.getTotal(),
                req.getLoanDate(),
                req.getTenor(),
                LoanStatus.NOT_PAID,
                createdTime
        );

        LoanEntity resLoanEntity = loanRepository.save(loanEntity);
        System.out.println("======== req =========");
        System.out.println(req);

        System.out.println("======== resTrxEntity =========");
        System.out.println(resTrxEntity);

        System.out.println("======== loanEntity =========");
        System.out.println(loanEntity);
    }
}
