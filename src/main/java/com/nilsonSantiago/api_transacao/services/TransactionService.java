package com.nilsonSantiago.api_transacao.services;

import com.nilsonSantiago.api_transacao.domain.Transaction;
import com.nilsonSantiago.api_transacao.dtos.RequestTransactionDto;
import com.nilsonSantiago.api_transacao.mappers.TransactionMapper;
import com.nilsonSantiago.api_transacao.repository.TransactionRepository;
import com.nilsonSantiago.api_transacao.services.exceptions.UnprocessableEntityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    public void insert(RequestTransactionDto requestTransactionDto) {

        TransactionMapper transactionMapper = new TransactionMapper();

        Transaction transaction = transactionMapper.toTransaction(requestTransactionDto);

        if(transaction.getValor() == null || transaction.getDatHora() == null) {
            throw new UnprocessableEntityException("Não é permitido valores vazios");
        }

        if(transaction.getDatHora().isAfter(OffsetDateTime.now())) {
            throw new UnprocessableEntityException("Transação não ocorrer no futuro");
        }

        if(transaction.getValor() < 0.0) {
            throw new UnprocessableEntityException("O valor não deve ser negativo");
        }

        transactionRepository.getTransactions().add(transaction);
    }

    public void delete() {
        transactionRepository.getTransactions().clear();
    }

}