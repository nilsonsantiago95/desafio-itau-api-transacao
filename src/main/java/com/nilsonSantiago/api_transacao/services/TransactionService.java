package com.nilsonSantiago.api_transacao.services;

import com.nilsonSantiago.api_transacao.domain.Transaction;
import com.nilsonSantiago.api_transacao.dtos.RequestTransactionDto;
import com.nilsonSantiago.api_transacao.mappers.TransactionMapper;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionService {

    private final List<Transaction> transactions = new ArrayList<>();

    public void insert(RequestTransactionDto requestTransactionDto) {

        TransactionMapper transactionMapper = new TransactionMapper();

        Transaction transaction = transactionMapper.toTransaction(requestTransactionDto);

        if(transaction.getValor() == null || transaction.getDatHora() == null) {
            throw new IllegalArgumentException("Não é permitido valores vazios");
        }

        if(transaction.getDatHora().isAfter(OffsetDateTime.now())) {
            throw new IllegalArgumentException("Transação não ocorrer no futuro");
        }

        if(transaction.getValor() < 0.0) {
            throw new IllegalArgumentException("O valor não deve ser negativo");
        }

        transactions.add(transaction);
    }

    public void delete(Transaction transaction) {
        transactions.remove(transaction);
    }

}