package com.pqnoje.notifacao.service;

import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.model.PublishRequest;
import com.pqnoje.notifacao.domain.Proposta;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class NotificacaoSNSService {

    private final AmazonSNS amazonSNS;

    public void notificar(String telefone, String mensagem){
        PublishRequest publishRequest = new PublishRequest().withMessage(mensagem).withPhoneNumber(telefone);
        this.amazonSNS.publish(publishRequest);
    }
}
