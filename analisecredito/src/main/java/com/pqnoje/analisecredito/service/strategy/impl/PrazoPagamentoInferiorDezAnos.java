package com.pqnoje.analisecredito.service.strategy.impl;

import com.pqnoje.analisecredito.domain.Proposta;
import com.pqnoje.analisecredito.service.strategy.CalculoPonto;
import org.springframework.stereotype.Component;

@Component
public class PrazoPagamentoInferiorDezAnos implements CalculoPonto {

    @Override
    public int calcular(Proposta proposta) {
        return proposta.getPrazoPagamento() < 120 ? 80 : 0;
    }
}