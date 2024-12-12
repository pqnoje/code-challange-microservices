package com.pqnoje.propostaapp.service;

import com.pqnoje.propostaapp.entity.Proposta;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class NotificacaoRabbitMQService {

    private RabbitTemplate rabbitTemplate;

    public void notificar(Proposta proposta, String exchange){
        this.rabbitTemplate.convertAndSend(exchange, "", proposta);
    }
}
