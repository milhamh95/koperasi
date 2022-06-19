package com.app.Koperasi.usecase;

import com.app.Koperasi.entity.*;
import com.app.Koperasi.repository.InstallmentRepository;
import com.app.Koperasi.repository.LoanRepository;
import com.app.Koperasi.repository.TransactionRepository;
import com.app.Koperasi.request.ApplyLoanRequest;
import com.app.Koperasi.request.PayInstallmentRequest;
import com.app.Koperasi.response.ApplyLoanResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Service
public class LoanUsecase {

    private final TransactionRepository transactionRepository;
    private final LoanRepository loanRepository;

    private final InstallmentRepository installmentRepository;

    @Autowired
    public LoanUsecase(TransactionRepository transactionRepository, LoanRepository loanRepository, InstallmentRepository installmentRepository) {
        this.transactionRepository = transactionRepository;
        this.loanRepository = loanRepository;
        this.installmentRepository = installmentRepository;
    }

    @Transactional
    public ApplyLoanResponse applyLoan(ApplyLoanRequest req) {
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
                req.getMemberId(),
                req.getTotal(),
                req.getLoanDate(),
                req.getTenor(),
                LoanStatus.NOT_PAID,
                createdTime
        );

        LoanEntity resLoanEntity = loanRepository.save(loanEntity);

        ApplyLoanResponse applyLoanResponse = new ApplyLoanResponse(
                resLoanEntity.getId(),
                resLoanEntity.getMemberId(),
                resLoanEntity.getTotal(),
                resLoanEntity.getLoanDate(),
                resLoanEntity.getTenor(),
                resLoanEntity.getStatus(),
                resLoanEntity.getCreatedTime()
        );

        return applyLoanResponse;
    }

    @Transactional
    public void PayInstallment(PayInstallmentRequest req, Long loanId) {
//        LocalDateTime createdTime = LocalDateTime.now();
//
//        Optional<InstallmentEntity> latestUserInstallment = installmentRepository.findLatestInstallment(loanId);
//
//        Optional<LoanEntity> loanEntity = loanRepository.findById(loanId);
////        if (loanEntity.get().getStatus() == LoanStatus.PAID) {
////            throw new Exception("loan is already paid");
////        }
//
//        TransactionEntity trxEntity = new TransactionEntity(
//                req.getMemberId(),
//                TransactionType.INSTALLMENT,
//                req.getTotal(),
//                createdTime
//        );
//
//        transactionRepository.save(trxEntity);
//
//        Integer currentLoanRemainder = loanEntity.get().getTotal();
//        if (latestUserInstallment.get().getLoan_remainder() != loanEntity.get().getTotal()) {
//            currentLoanRemainder = latestUserInstallment.get().getLoan_remainder();
//        }
//
//        Integer loanRemainder = currentLoanRemainder - req.getTotal();
//        if (loanRemainder < 0) {
//            loanRemainder = 0;
//        }
//        InstallmentEntity installmentEntity = new InstallmentEntity(
//                loanId,
//                req.getTotal(),
//                loanRemainder,
//                createdTime
//        );
//
////        InstallmentEntity resInstallmentEntity = installmentRepository.save(installmentEntity);
//        installmentRepository.save(installmentEntity);
    }

}
