package com.pqnoje.propostaapp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.mapstruct.Mapping;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PropostaResponseDTO {

    private String id;
    private String nome;
    private String sobrenome;
    private String telefone;
    private String cpf;
    private Double renda;
    private Double valorSolicitado;
    private String cep;
    private String logradouro;
    private String complemento;
    private String bairro;
    private String localidade;
    private String uf;
    private String numero;
    private int prazoPagamento;
    private Boolean aprovada;
    private String observacao;
}
