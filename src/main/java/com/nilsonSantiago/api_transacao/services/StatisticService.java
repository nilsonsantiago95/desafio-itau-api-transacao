package com.nilsonSantiago.api_transacao.services;

import com.nilsonSantiago.api_transacao.domain.Statistic;
import com.nilsonSantiago.api_transacao.domain.Transaction;
import com.nilsonSantiago.api_transacao.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.DoubleSummaryStatistics;
import java.util.stream.Collectors;

@Service
public class StatisticService {

    @Autowired
    private TransactionRepository transactionRepository;

    public Statistic findStatistic(Integer seconds) {

        OffsetDateTime timeLimit = OffsetDateTime.now().minusSeconds(seconds);

        DoubleSummaryStatistics stats = transactionRepository.getTransactions().stream()
                .filter(transaction -> transaction.getDatHora().isAfter(timeLimit))
                .collect(Collectors.summarizingDouble(Transaction::getValor));

        return new Statistic(stats.getCount(), stats.getSum(), stats.getAverage(), countIsZero(stats, stats.getMin()), countIsZero(stats, stats.getMax()));
    }

    private static Double countIsZero(DoubleSummaryStatistics stats, Double value) {
        return stats.getCount() == 0L ? 0.0 : value;
    }

}