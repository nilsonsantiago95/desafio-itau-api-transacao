package com.nilsonSantiago.api_transacao.controller;

import com.nilsonSantiago.api_transacao.domain.Transaction;
import com.nilsonSantiago.api_transacao.dtos.RequestTransactionDto;
import com.nilsonSantiago.api_transacao.services.TransactionService;
import com.nilsonSantiago.api_transacao.services.exceptions.UnprocessableEntityException;

import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/transacao")
@Slf4j
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping
    public ResponseEntity<Transaction> insert(@RequestBody RequestTransactionDto requestTransactionDto) {
        try {
            transactionService.insert(requestTransactionDto);
        } catch(UnprocessableEntityException error) {
            log.warn("Falha ao registrar a transação. erro: {}", error);
        }
        return ResponseEntity.created(UriComponentsBuilder.fromPath("/transacao").build().toUri()).build();
    }

    @DeleteMapping
    public ResponseEntity<Void> delete() {
        try {
            transactionService.delete();
        } catch(NoSuchElementException error) {
            log.warn("Não foi possível remover as transações. Erro: {}", error);
        }

        return ResponseEntity.ok().build();
    }

}