package com.pqnoje.notifacao.listener;

import com.pqnoje.notifacao.contants.MensagemConstante;
import com.pqnoje.notifacao.domain.Proposta;
import com.pqnoje.notifacao.service.NotificacaoSNSService;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class PropostaConcluidaListener {

    private final NotificacaoSNSService notificacaoSNSService;

    @RabbitListener(queues = "${rabbitmq.queue.proposta.concluida}")
    public void propostaConcluida(Proposta proposta) {
        String mensagem = null;
        if(proposta.getAprovada()){
            mensagem = String.format(MensagemConstante.PROPOSTA_APROVADA, proposta.getUsuario().getNome());
        }else{
            mensagem = String.format(MensagemConstante.PROPOSTA_NEGADA, proposta.getUsuario().getNome(), proposta.getObservacao());
        }

        this.notificacaoSNSService.notificar(proposta.getUsuario().getTelefone(), mensagem);
    }
}
