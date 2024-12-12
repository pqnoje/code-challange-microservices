package com.pqnoje.propostaapp.repository;

import com.pqnoje.propostaapp.entity.Proposta;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


public interface PropostaRepository extends CrudRepository<Proposta, Long> {
    Iterable<Proposta> findAllByIntegradaIsFalse();

    @Transactional
    @Modifying
    @Query(value = "UPDATE proposta SET aprovada = :aprovada, observacao = :observacao WHERE id = :id", nativeQuery = true)
    void atualizarProposta(Long id, boolean aprovada, String observacao);
}
