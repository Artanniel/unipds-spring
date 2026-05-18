package com.artantech.unipdsspring.service;

import com.artantech.unipdsspring.model.Transaction;
import com.artantech.unipdsspring.model.dto.TransferDTO;

public interface ITransferService {

    Transaction transferValues(TransferDTO transfer);

}
