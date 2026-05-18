package com.artantech.unipdsspring.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.artantech.unipdsspring.model.Transaction;
import com.artantech.unipdsspring.model.dto.TransferDTO;
import com.artantech.unipdsspring.service.ITransferService;

@RestController
@RequestMapping("/transfer")
@CrossOrigin
public class TransferController {

    private ITransferService service;

    public TransferController(ITransferService service) {
        super();
        this.service = service;
    }

    @PostMapping("/transfer")
    public ResponseEntity<Transaction> transferValues(@RequestBody TransferDTO transfer) {
        return ResponseEntity.status(200).body(service.transferValues(transfer));
    }

}
