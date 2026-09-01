package com.nilsonSantiago.api_transacao.services;

import com.nilsonSantiago.api_transacao.domain.Transaction;
import com.nilsonSantiago.api_transacao.dtos.RequestTransactionDto;
import com.nilsonSantiago.api_transacao.mappers.TransactionMapper;
import com.nilsonSantiago.api_transacao.repository.TransactionRepository;
import com.nilsonSantiago.api_transacao.services.exceptions.UnprocessableEntityException;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.NoSuchElementException;

@Slf4j
@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public void insert(RequestTransactionDto requestTransactionDto) {

        TransactionMapper transactionMapper = new TransactionMapper();

        if(requestTransactionDto.valor() == null || requestTransactionDto.dataHora() == null) {
            throw new UnprocessableEntityException("Não é permitido valores vazios");
        }

        if(OffsetDateTime.parse(requestTransactionDto.dataHora()).isAfter(OffsetDateTime.now())) {
            throw new UnprocessableEntityException("Transação não pode ocorrer no futuro");
        }

        if(requestTransactionDto.valor() < 0.0) {
            throw new UnprocessableEntityException("O valor não deve ser negativo");
        }

        Transaction transaction = transactionMapper.toTransaction(requestTransactionDto);

        transactionRepository.getTransactions().add(transaction);

        log.info("Transação recebida com sucesso. Valor {}, dataHora {}", transaction.getValor(), transaction.getDatHora());
    }

    public void delete() {

        if(transactionRepository.getTransactions().isEmpty()) {
            throw new NoSuchElementException("Não transações para serem removidas");
        }
        transactionRepository.getTransactions().clear();
        log.info("Transações removidas com sucesso");
    }

}