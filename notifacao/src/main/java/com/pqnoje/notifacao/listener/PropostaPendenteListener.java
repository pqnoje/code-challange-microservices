package com.pqnoje.notifacao.listener;

import com.pqnoje.notifacao.contants.MensagemConstante;
import com.pqnoje.notifacao.domain.Proposta;
import com.pqnoje.notifacao.service.NotificacaoSNSService;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class PropostaPendenteListener {

    private final NotificacaoSNSService notificacaoSNSService;

    @RabbitListener(queues = "${rabbitmq.queue.proposta.pendente}")
    public void propostaPendente(Proposta proposta) {
        String messagem = String.format(MensagemConstante.PROPOSTA_EM_ANALISE, proposta.getUsuario().getNome());
        this.notificacaoSNSService.notificar(proposta.getUsuario().getTelefone(), messagem);
    }
}
