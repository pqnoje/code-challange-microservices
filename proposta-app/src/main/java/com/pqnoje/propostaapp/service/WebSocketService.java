package com.pqnoje.propostaapp.service;

import com.pqnoje.propostaapp.dto.PropostaResponseDTO;
import lombok.AllArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class WebSocketService {

    private SimpMessagingTemplate template;

    public void notificar(PropostaResponseDTO proposta) {
        this.template.convertAndSend("/propostas", proposta);
    }
}