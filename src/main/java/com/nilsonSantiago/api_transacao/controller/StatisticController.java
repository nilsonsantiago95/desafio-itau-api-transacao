package com.nilsonSantiago.api_transacao.controller;

import com.nilsonSantiago.api_transacao.domain.Statistic;
import com.nilsonSantiago.api_transacao.services.StatisticService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/estatistica")
public class StatisticController {

    private final StatisticService statisticService;

    public StatisticController(StatisticService statisticService) {
        this.statisticService = statisticService;
    }

    @GetMapping
    public ResponseEntity<Statistic> findStatistic(@RequestParam(value = "seconds", defaultValue = "60") Integer seconds) {
        Statistic statistic = statisticService.findStatistic(seconds);
        return ResponseEntity.ok(statistic);
    }

}