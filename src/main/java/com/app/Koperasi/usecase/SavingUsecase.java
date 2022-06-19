package com.app.Koperasi.usecase;

import com.app.Koperasi.entity.SavingEntity;
import com.app.Koperasi.entity.TransactionEntity;
import com.app.Koperasi.entity.TransactionType;
import com.app.Koperasi.repository.SavingRepository;
import com.app.Koperasi.repository.TransactionRepository;
import com.app.Koperasi.request.SaveMoneyRequest;
import com.app.Koperasi.request.WithdrawMoneyRequest;
import com.app.Koperasi.response.SaveMoneyResponse;
import com.app.Koperasi.response.WithdrawMoneyResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SavingUsecase {
    private final TransactionRepository transactionRepository;

    private final SavingRepository savingRepository;

    @Autowired
    public SavingUsecase(TransactionRepository transactionRepository, SavingRepository savingRepository) {
        this.transactionRepository = transactionRepository;
        this.savingRepository = savingRepository;
    }

    public void validateSaveMoney(SaveMoneyRequest req) {
        if (req.getTotal() < 10000) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "minimum amount of money to save is 10000"
            );
        }
    }

    public SaveMoneyResponse saveMoney(SaveMoneyRequest req) {
        validateSaveMoney(req);

        LocalDateTime createdTime = LocalDateTime.now();

        TransactionEntity trxEntity = new TransactionEntity(
                req.getMemberId(),
                TransactionType.SAVE,
                req.getTotal(),
                createdTime
        );

        TransactionEntity resTrxEntity = transactionRepository.save(trxEntity);

        List<SavingEntity> currentSavingEntity = savingRepository.findLatestSaving(req.getMemberId());

        Integer currentSaving = req.getTotal();
        if (currentSavingEntity.size() > 0) {
            Integer latestCurrentSaving = currentSavingEntity.get(0).getCurrentSaving();
            currentSaving += latestCurrentSaving;
        }

        SavingEntity savingEntity = new SavingEntity(
                req.getMemberId(),
                resTrxEntity.getId(),
                TransactionType.SAVE,
                req.getTotal(),
                currentSaving,
                createdTime
        );

        SavingEntity resSavingEntity = savingRepository.save(savingEntity);

        return new SaveMoneyResponse(
                resSavingEntity.getId(),
                resSavingEntity.getMemberId(),
                resSavingEntity.getTotal(),
                currentSaving,
                createdTime
        );
    }

    public WithdrawMoneyResponse withdrawMoney(WithdrawMoneyRequest req, Long memberId) {
        LocalDateTime createdTime = LocalDateTime.now();

        TransactionEntity trxEntity = new TransactionEntity(
                memberId,
                TransactionType.WITHDRAW,
                req.getTotal(),
                createdTime
        );

        TransactionEntity resTrxEntity = transactionRepository.save(trxEntity);

        List<SavingEntity> currentSavingEntity = savingRepository.findLatestSaving(memberId);

        if (currentSavingEntity.size() == 0) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "your saving is 0"
            );
        }

        Integer currentSaving = currentSavingEntity.get(0).getCurrentSaving();
        if (req.getTotal() > currentSaving) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "your saving is not enough"
            );
        }
        currentSaving = currentSaving - req.getTotal();

        SavingEntity savingEntity = new SavingEntity(
                memberId,
                resTrxEntity.getId(),
                TransactionType.WITHDRAW,
                req.getTotal(),
                currentSaving,
                createdTime
        );

        SavingEntity resSavingEntity = savingRepository.save(savingEntity);

        return new WithdrawMoneyResponse(
                resSavingEntity.getId(),
                resSavingEntity.getMemberId(),
                resSavingEntity.getTotal(),
                currentSaving,
                createdTime
        );
    }
}
