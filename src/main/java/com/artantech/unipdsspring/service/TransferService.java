package com.artantech.unipdsspring.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.artantech.unipdsspring.events.InvalidAccountException;
import com.artantech.unipdsspring.model.Account;
import com.artantech.unipdsspring.model.Transaction;
import com.artantech.unipdsspring.model.dto.TransferDTO;
import com.artantech.unipdsspring.repository.AccountRepo;
import com.artantech.unipdsspring.repository.TransactionRepo;

import jakarta.transaction.Transactional;

@Service
public class TransferService implements ITransferService {

    private AccountRepo accountRepo;
    private TransactionRepo transactionRepo;

    TransferService(AccountRepo accountRepo, TransactionRepo transactionRepo) {
        super();
        this.accountRepo = accountRepo;
        this.transactionRepo = transactionRepo;
    }

    @Override
    @Transactional
    public Transaction transferValues(TransferDTO transfer) {
        Account src = accountRepo.findById(transfer.debitAccountNumber()).orElseThrow(
                () -> new InvalidAccountException("Account #" + transfer.debitAccountNumber() + " not found"));

        Account dst = accountRepo.findById(transfer.creditAccountNumber()).orElseThrow(
                () -> new InvalidAccountException("Account #" + transfer.creditAccountNumber() + " not found"));

        dst.setBalance(dst.getBalance() + transfer.amount());
        accountRepo.save(dst);

        src.setBalance(src.getBalance() - transfer.amount());
        accountRepo.save(src);

        Transaction tx = new Transaction();
        tx.setAmount(transfer.amount());
        tx.setCreditAccount(dst);
        tx.setDebitAccount(src);
        tx.setDate(LocalDateTime.now());
        tx.setStatus("Completed");
        return transactionRepo.save(tx);
    }

}
