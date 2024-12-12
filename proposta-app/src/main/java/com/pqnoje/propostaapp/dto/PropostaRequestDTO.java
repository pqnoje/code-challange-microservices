package com.pqnoje.propostaapp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PropostaRequestDTO {

    private Long id;
    private String nome;
    private String sobrenome;
    private String telefone;
    private String cpf;
    private Double renda;
    private String cep;
    private String numero;
    private String complemento;
    private Double valorSolicitado;
    private int prazoPagamento;
    private Boolean aprovado;
    private String observacao;
}
