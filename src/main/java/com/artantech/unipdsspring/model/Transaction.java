package com.artantech.unipdsspring.model;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "tbl_transaction")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_number")
    private Integer number;

    @Column(name = "status")
    private String status;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "date")
    private LocalDateTime date;

    @ManyToOne
    @JoinColumn(name = "credit_account_number")
    private Account creditAccount;

    @ManyToOne
    @JoinColumn(name = "debit_account_number")
    private Account debitAccount;

    public Integer getNumber() {
        return this.number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getAmount() {
        return this.amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public LocalDateTime getDate() {
        return this.date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Account getCreditAccount() {
        return this.creditAccount;
    }

    public void setCreditAccount(Account creditAccount) {
        this.creditAccount = creditAccount;
    }

    public Account getDebitAccount() {
        return this.debitAccount;
    }

    public void setDebitAccount(Account debitAccount) {
        this.debitAccount = debitAccount;
    }

}
