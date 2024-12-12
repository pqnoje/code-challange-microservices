package com.pqnoje.propostaapp.agendador;

import com.pqnoje.propostaapp.entity.Proposta;
import com.pqnoje.propostaapp.repository.PropostaRepository;
import com.pqnoje.propostaapp.service.NotificacaoRabbitMQService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class PropostaSemIntegracao {

    private final PropostaRepository propostaRepository;
    private final NotificacaoRabbitMQService notificacaoRabbitMQService;
    private final String exchange;
    private final Logger logger = LoggerFactory.getLogger(PropostaSemIntegracao.class);

    public PropostaSemIntegracao(
            PropostaRepository propostaRepository,
            NotificacaoRabbitMQService notificacaoRabbitMQService,
            @Value("${rabbitmq.propostapendente.exchange}")String exchange) {
        this.propostaRepository = propostaRepository;
        this.notificacaoRabbitMQService = notificacaoRabbitMQService;
        this.exchange = exchange;
    }

    @Scheduled(fixedDelay = 10, timeUnit = TimeUnit.SECONDS)
    public void buscarPropostasSemIntegracao(){
         this.propostaRepository.findAllByIntegradaIsFalse().forEach(proposta -> {
            try{
                proposta.setIntegrada(true);
                this.notificacaoRabbitMQService.notificar(proposta, this.exchange);
                this.atualizaProposta(proposta);
            }catch(RuntimeException ex){
                this.logger.error(ex.getMessage());
            }
        });
    }

    private void atualizaProposta(Proposta proposta){
        this.propostaRepository.save(proposta);
    }
}
