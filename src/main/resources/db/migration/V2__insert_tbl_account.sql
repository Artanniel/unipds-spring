CREATE TABLE tbl_account (
    account_number INT AUTO_INCREMENT PRIMARY KEY,
    owner_name VARCHAR(255),
    balance DOUBLE
);

CREATE TABLE tbl_transaction (
    transaction_number INT AUTO_INCREMENT PRIMARY KEY,
    status VARCHAR(50),
    amount DOUBLE,
    date TIMESTAMP,
    credit_account_number INT,
    debit_account_number INT,
    CONSTRAINT fk_credit_account FOREIGN KEY (credit_account_number) REFERENCES tbl_account(account_number),
    CONSTRAINT fk_debit_account FOREIGN KEY (debit_account_number) REFERENCES tbl_account(account_number)
);

INSERT INTO tbl_account (owner_name, balance) VALUES ('ACC001', 100.00);
INSERT INTO tbl_account (owner_name, balance) VALUES ('ACC002', 50.00);
