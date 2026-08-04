package com.nilsonSantiago.api_transacao.mappers;

import com.nilsonSantiago.api_transacao.domain.Transaction;
import com.nilsonSantiago.api_transacao.dtos.RequestTransactionDto;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.OffsetDateTime;

public class TransactionMapper {

    public TransactionMapper() {

    }

    public Transaction toTransaction(RequestTransactionDto requestTransactionDto) {
        Transaction transaction = new Transaction(requestTransactionDto.valor(), OffsetDateTime.parse(requestTransactionDto.dataHora()));
        return transaction;
    }

}