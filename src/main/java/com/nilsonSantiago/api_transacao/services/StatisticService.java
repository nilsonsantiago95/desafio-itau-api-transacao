package com.nilsonSantiago.api_transacao.services;

import com.nilsonSantiago.api_transacao.domain.Statistic;
import com.nilsonSantiago.api_transacao.domain.Transaction;
import com.nilsonSantiago.api_transacao.repository.TransactionRepository;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.DoubleSummaryStatistics;
import java.util.stream.Collectors;

@Slf4j
@Service
public class StatisticService {

    private final TransactionRepository transactionRepository;

    public StatisticService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public Statistic findStatistic(Integer seconds) {

        OffsetDateTime timeLimit = OffsetDateTime.now().minusSeconds(seconds);

        DoubleSummaryStatistics stats = transactionRepository.getTransactions().stream()
                .filter(transaction -> transaction.getDatHora().isAfter(timeLimit))
                .collect(Collectors.summarizingDouble(Transaction::getValor));

        log.info("Estatistica exibida com sucesso");
        return new Statistic(stats.getCount(), stats.getSum(), stats.getAverage(), countIsZero(stats, stats.getMin()), countIsZero(stats, stats.getMax()));
    }

    private static Double countIsZero(DoubleSummaryStatistics stats, Double value) {
        return stats.getCount() == 0L ? 0.0 : value;
    }

}