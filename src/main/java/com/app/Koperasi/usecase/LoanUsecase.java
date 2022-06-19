package com.app.Koperasi.usecase;

import com.app.Koperasi.entity.*;
import com.app.Koperasi.repository.InstallmentRepository;
import com.app.Koperasi.repository.LoanRepository;
import com.app.Koperasi.repository.TransactionRepository;
import com.app.Koperasi.request.ApplyLoanRequest;
import com.app.Koperasi.request.PayInstallmentRequest;
import com.app.Koperasi.response.ApplyLoanResponse;
import com.app.Koperasi.response.PayInstallmentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;

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
    public PayInstallmentResponse PayInstallment(PayInstallmentRequest req, Long loanId) {
        LocalDateTime createdTime = LocalDateTime.now();

        LoanEntity loanEntity = loanRepository.findById(loanId).
                orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "loan is not found"
        ));

        if (loanEntity.getStatus() == LoanStatus.PAID) {
            throw new ResponseStatusException(
                    HttpStatus.OK, "loan is paid"
            );
        }

        TransactionEntity trxEntity = new TransactionEntity(
                loanEntity.getMemberId(),
                TransactionType.INSTALLMENT,
                req.getTotal(),
                createdTime
        );
        transactionRepository.save(trxEntity);

        Optional<InstallmentEntity> latestUserInstallment = installmentRepository.findTopByLoanId(loanId);

        Integer currentLoanRemainder = loanEntity.getTotal();
        if (latestUserInstallment.isPresent() &&
                latestUserInstallment.get().getLoanRemainder() < loanEntity.getTotal()){
            currentLoanRemainder = latestUserInstallment.get().getLoanRemainder();
        }

        Integer loanRemainder = currentLoanRemainder - req.getTotal();
        if (loanRemainder < 0) {
            loanRemainder = 0;
        }
        InstallmentEntity installmentEntity = new InstallmentEntity(
                loanId,
                req.getTotal(),
                loanRemainder,
                createdTime
        );

        installmentRepository.save(installmentEntity);

        if (loanRemainder == 0) {
            loanEntity.setStatus(LoanStatus.PAID);
        }

        PayInstallmentResponse payInstallmentResponse = new PayInstallmentResponse(
            installmentEntity.getId(),
                loanEntity.getMemberId(),
                installmentEntity.getTotal(),
                installmentEntity.getCreatedTime(),
                installmentEntity.getLoanRemainder(),
                loanEntity.getStatus()
        );

        return payInstallmentResponse;
    }

}
