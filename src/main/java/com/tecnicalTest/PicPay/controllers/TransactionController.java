package com.tecnicalTest.PicPay.controllers;

import com.tecnicalTest.PicPay.model.transaction.Transaction;
import com.tecnicalTest.PicPay.dtos.TransactionDTO;
import com.tecnicalTest.PicPay.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping
    public ResponseEntity<Transaction> createTransaction(@RequestBody TransactionDTO transactionDTO) throws Exception {
        Transaction transaction = transactionService.createTransaction(transactionDTO);

        return new ResponseEntity<>(transaction, HttpStatus.CREATED);

    }

}
