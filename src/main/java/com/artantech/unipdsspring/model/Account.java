package com.artantech.unipdsspring.model;

import com.artantech.unipdsspring.events.InvalidBalanceAccountException;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tbl_account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_number")
    private Integer number;

    @Column(name = "owner_name")
    private String ownerName;

    @Column(name = "balance")
    private Double balance;

    public Integer getNumber() {
        return this.number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getOwnerName() {
        return this.ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public Double getBalance() {
        return this.balance;
    }

    public void setBalance(Double balance) {
        if (balance < 0) {
            throw new InvalidBalanceAccountException("Balance cannot be negative");
        }
        this.balance = balance;
    }

}
