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
import java.time.LocalDate;
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

    public void validateLoan(ApplyLoanRequest req) {
        LocalDate currentDate = LocalDate.now();
        if (req.getLoanDate().isBefore(currentDate)) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "loan date should not be before today"
            );
        }

        if (req.getTenor().isBefore(currentDate)) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "tenor should not be before today"
            );
        }

        if (req.getTenor().isBefore(req.getLoanDate())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "tenor should not be before loan date"
            );
        }

        if (req.getTotal() < 0 ) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "invalid loan total"
            );
        }
    }

    @Transactional
    public ApplyLoanResponse applyLoan(ApplyLoanRequest req) {
        validateLoan(req);
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

    public void validateInstallment(PayInstallmentRequest req, LoanEntity loan) {
        if (loan.getStatus() == LoanStatus.PAID) {
            throw new ResponseStatusException(
                    HttpStatus.OK, "loan is already paid"
            );
        }

        if (req.getInstallmentDate().isBefore(loan.getLoanDate())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "installment should be after loan date"
            );
        }

        if (req.getInstallmentDate().isAfter(loan.getTenor())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "installment date should not be after tenor"
            );
        }

        if (req.getTotal() < 0) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "invalid installment total"
            );
        }

        if (req.getTotal() > loan.getTotal()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "total installment should less or equal with loan"
            );
        }
    }

    @Transactional
    public PayInstallmentResponse payInstallment(PayInstallmentRequest req, Long loanId) {
        LocalDateTime createdTime = LocalDateTime.now();

        LoanEntity loanEntity = loanRepository.findById(loanId).
                orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "loan is not found"
        ));

        validateInstallment(req, loanEntity);

        TransactionEntity trxEntity = new TransactionEntity(
                loanEntity.getMemberId(),
                TransactionType.INSTALLMENT,
                req.getTotal(),
                createdTime
        );

        TransactionEntity resTrx = transactionRepository.save(trxEntity);

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
                resTrx.getId(),
                req.getTotal(),
                loanRemainder,
                createdTime
        );

        installmentRepository.save(installmentEntity);

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
