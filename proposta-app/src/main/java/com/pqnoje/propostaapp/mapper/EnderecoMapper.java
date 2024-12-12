package com.pqnoje.propostaapp.mapper;

import com.pqnoje.propostaapp.dto.EnderecoResponseDTO;
import com.pqnoje.propostaapp.dto.PropostaRequestDTO;
import com.pqnoje.propostaapp.dto.PropostaResponseDTO;
import com.pqnoje.propostaapp.entity.Endereco;
import com.pqnoje.propostaapp.entity.Proposta;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EnderecoMapper {

    public Endereco convertDTOToEndereco(EnderecoResponseDTO enderecoResponseDTO);

}
