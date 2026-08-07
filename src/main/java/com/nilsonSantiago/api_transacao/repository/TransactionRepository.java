package com.nilsonSantiago.api_transacao.repository;

import com.nilsonSantiago.api_transacao.domain.Transaction;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TransactionRepository {

    private final List<Transaction> transactions = new ArrayList<>();

    public TransactionRepository() {
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

}