package com.pqnoje.propostaapp.service;

import com.pqnoje.propostaapp.dto.EnderecoResponseDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class EnderecoService {

    public EnderecoResponseDTO buscarEnderecoPorCEP(String cep){
        RestTemplate restTemplate = new RestTemplate();
        EnderecoResponseDTO enderecoResponseDTO = restTemplate.getForObject("https://opencep.com/v1/" + cep, EnderecoResponseDTO.class);
        return enderecoResponseDTO;
    }
}
