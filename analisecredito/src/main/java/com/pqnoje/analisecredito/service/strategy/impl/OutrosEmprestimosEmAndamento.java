package com.pqnoje.analisecredito.service.strategy.impl;

import com.pqnoje.analisecredito.domain.Proposta;
import com.pqnoje.analisecredito.service.strategy.CalculoPonto;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class OutrosEmprestimosEmAndamento implements CalculoPonto {

    @Override
    public int calcular(Proposta proposta) {
        return outrosEmprestimosEmAndamento() ? 0 : 80;
    }

    private boolean outrosEmprestimosEmAndamento() {
        return new Random().nextBoolean();
    }
}