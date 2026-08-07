package com.nilsonSantiago.api_transacao.domain;

import java.time.OffsetDateTime;

public class Transaction {

    private Double valor;
    private OffsetDateTime datHora;

    public Transaction() {
    }

    public Transaction(Double valor, OffsetDateTime datHora) {
        this.valor = valor;
        this.datHora = datHora;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public OffsetDateTime getDatHora() {
        return datHora;
    }

    public void setDatHora(OffsetDateTime datHora) {
        this.datHora = datHora;
    }
}