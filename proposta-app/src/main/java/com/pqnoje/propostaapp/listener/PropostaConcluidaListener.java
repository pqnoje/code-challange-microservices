package com.pqnoje.propostaapp.listener;

import com.pqnoje.propostaapp.dto.PropostaResponseDTO;
import com.pqnoje.propostaapp.entity.Proposta;
import com.pqnoje.propostaapp.mapper.PropostaMapper;
import com.pqnoje.propostaapp.repository.PropostaRepository;
import com.pqnoje.propostaapp.service.WebSocketService;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class PropostaConcluidaListener {

    private PropostaRepository propostaRepository;

    private WebSocketService webSocketService;

    private PropostaMapper propostaMapper;

    @RabbitListener(queues = "${rabbitmq.queue.proposta.concluida}")
    public void propostaConcluida(Proposta proposta) {
        this.propostaRepository.atualizarProposta(proposta.getId(), proposta.getAprovada(), proposta.getObservacao());
        PropostaResponseDTO response = this.propostaMapper.convertEntityToDTO(proposta);
        this.webSocketService.notificar(response);
    }
}