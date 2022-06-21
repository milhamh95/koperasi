package com.app.Koperasi.usecase;

import com.app.Koperasi.entity.TransactionDetailEntity;
import com.app.Koperasi.repository.TransactionDetailRepository;
import com.app.Koperasi.response.TransactionDetailResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionUsecase {
    private final TransactionDetailRepository transactionDetailRepository;

    @Autowired
    public TransactionUsecase(TransactionDetailRepository transactionDetailRepository) {
        this.transactionDetailRepository = transactionDetailRepository;
    }

    private void validateFilterDate(LocalDate startDate, LocalDate endDate) {
        LocalDate currentDate = LocalDate.now();

        if (endDate.isBefore(startDate)) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "endDate should be greater than equal with startDate"
            );
        }

        if (endDate.isAfter(currentDate)) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "endDate should be less than equal with current date"
            );
        }
    }

    public List<TransactionDetailResponse> fetchTransactions(LocalDate startDate, LocalDate endDate) {
        List<TransactionDetailEntity> listTrxs = new ArrayList<>();

        if (startDate == null || endDate == null) {
            listTrxs = transactionDetailRepository.findTransactions();
        } else {
            validateFilterDate(startDate, endDate);
            LocalDate newEndDate = endDate.plusDays(1);
            listTrxs = transactionDetailRepository.findTransactionsByDate(startDate, newEndDate);
        }

        List<TransactionDetailResponse> trxs = new ArrayList<>();

        for (TransactionDetailEntity trxDetail: listTrxs) {
            TransactionDetailResponse trx = new TransactionDetailResponse(
                trxDetail.getId(),
                    trxDetail.getMemberId(),
                    trxDetail.getName(),
                    trxDetail.getType(),
                    trxDetail.getTotal(),
                    trxDetail.getCreatedTime()
            );
            trx.setRefId(
                    trxDetail.getLoanId(),
                    trxDetail.getInstallmentId(),
                    trxDetail.getSavingId()
            );

            trxs.add(trx);
        }

        return trxs;
    }
}
