package com.app.Koperasi.usecase;

import com.app.Koperasi.entity.SavingEntity;
import com.app.Koperasi.entity.TransactionEntity;
import com.app.Koperasi.entity.TransactionType;
import com.app.Koperasi.repository.SavingRepository;
import com.app.Koperasi.request.SaveMoneyRequest;
import com.app.Koperasi.response.SaveMoneyResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;

public class SavingUsecase {

    private final SavingRepository savingRepository;

    @Autowired
    public SavingUsecase(SavingRepository savingRepository) {
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

        SavingEntity savingEntity = new SavingEntity(
                trxEntity.getId(),
                req.getMemberId(),
                TransactionType.SAVE,
                req.getTotal(),
                0,
                createdTime
        );

        SavingEntity resSavingEntity = savingRepository.save(savingEntity);

        return new SaveMoneyResponse(
                resSavingEntity.getId(),
                resSavingEntity.getMemberId(),
                resSavingEntity.getTotal(),
                resSavingEntity.getCurrentSaving(),
                createdTime
        );
    }
}
