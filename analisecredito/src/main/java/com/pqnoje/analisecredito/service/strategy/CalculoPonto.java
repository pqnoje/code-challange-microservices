package com.pqnoje.analisecredito.service.strategy;

import com.pqnoje.analisecredito.domain.Proposta;

public interface CalculoPonto {

    int calcular(Proposta proposta);
}