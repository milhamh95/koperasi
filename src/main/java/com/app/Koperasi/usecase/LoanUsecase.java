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
import java.util.List;

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

        return new ApplyLoanResponse(
                resLoanEntity.getId(),
                resLoanEntity.getMemberId(),
                resLoanEntity.getTotal(),
                resLoanEntity.getLoanDate(),
                resLoanEntity.getTenor(),
                resLoanEntity.getStatus(),
                createdTime
        );
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
                    HttpStatus.OK, "loan is already paid"
            );
        }

        if (req.getInstallmentDate().isBefore(loanEntity.getLoanDate())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "installment should be after loan date"
            );
        }

        if (req.getInstallmentDate().isAfter(loanEntity.getTenor())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "installment should not be after tenor"
            );
        }

        if (req.getTotal() > loanEntity.getTotal()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "total installment should less than equal with loan"
            );
        }

        TransactionEntity trxEntity = new TransactionEntity(
                loanEntity.getMemberId(),
                TransactionType.INSTALLMENT,
                req.getTotal(),
                createdTime
        );
        transactionRepository.save(trxEntity);

        Integer currentLoanRemainder = loanEntity.getTotal();

        List<InstallmentEntity> latestUserInstallment = installmentRepository.findLatestInstallment(loanId);

        if (latestUserInstallment.size() > 0 ) {
            Integer userInstallmentRemainder = latestUserInstallment.get(0).getLoanRemainder();
            if (req.getTotal() > userInstallmentRemainder) {
                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST, "total installment should less than equal with loan remainder"
                );
            }

            currentLoanRemainder = userInstallmentRemainder;
        }


        Integer loanRemainder = currentLoanRemainder - req.getTotal();
        InstallmentEntity installmentEntity = new InstallmentEntity(
                loanId,
                req.getTotal(),
                loanRemainder,
                createdTime
        );

        installmentRepository.save(new InstallmentEntity(
                loanId,
                req.getTotal(),
                loanRemainder,
                createdTime
        ));

        if (loanRemainder == 0) {
            loanEntity.setStatus(LoanStatus.PAID);
        }

        return new PayInstallmentResponse(
                installmentEntity.getId(),
                loanEntity.getMemberId(),
                installmentEntity.getTotal(),
                createdTime,
                installmentEntity.getLoanRemainder(),
                loanEntity.getStatus()
        );
    }

}
