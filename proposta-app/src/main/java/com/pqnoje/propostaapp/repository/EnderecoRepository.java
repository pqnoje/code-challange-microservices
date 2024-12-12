package com.pqnoje.propostaapp.repository;

import com.pqnoje.propostaapp.entity.Endereco;
import org.springframework.data.repository.CrudRepository;

public interface EnderecoRepository extends CrudRepository<Endereco, Long> {
    public Endereco findByUsuarioId(Long id);
}
