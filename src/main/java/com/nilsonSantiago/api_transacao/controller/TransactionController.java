package com.nilsonSantiago.api_transacao.controller;

import com.nilsonSantiago.api_transacao.domain.Transaction;
import com.nilsonSantiago.api_transacao.dtos.RequestTransactionDto;
import com.nilsonSantiago.api_transacao.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.OffsetDateTime;

@RestController
@RequestMapping("/transacao")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping
    public ResponseEntity<Transaction> insert(@RequestBody RequestTransactionDto requestTransactionDto) {
        try {
            transactionService.insert(requestTransactionDto);
        } catch(IllegalArgumentException e) {
            return ResponseEntity.unprocessableContent().build();
        } catch(UnknownError e) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.created(UriComponentsBuilder.fromPath("/transacao").build().toUri()).build();
    }

}