package com.example.com.picPaySImplificado.controllers;


import com.example.com.picPaySImplificado.domain.transaction.Transaction;
import com.example.com.picPaySImplificado.dto.TransactionDTO;
import com.example.com.picPaySImplificado.service.TransectionService;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transactions")
public class TansactionController {
    @Autowired
    private TransectionService transectionService;

    @PostMapping
    public ResponseEntity<Transaction> createTransection(@RequestBody TransactionDTO transaction ) throws Exception {
        Transaction newTransaction = this.transectionService.createTransaction(transaction);

        return new ResponseEntity<>(newTransaction, HttpStatus.OK);
    }
}
