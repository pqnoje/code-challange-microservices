package com.pqnoje.propostaapp.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String sobrenome;
    private String cpf;
    private String telefone;
    private Double renda;

    @OneToOne(mappedBy = "usuario", cascade = CascadeType.PERSIST)
    @JsonBackReference
    private Proposta proposta;

    @OneToOne(mappedBy = "usuario", cascade = CascadeType.PERSIST)
    @JsonBackReference
    private Endereco endereco;

}
