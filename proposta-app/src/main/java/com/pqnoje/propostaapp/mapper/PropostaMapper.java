package com.pqnoje.propostaapp.mapper;

import com.pqnoje.propostaapp.dto.PropostaRequestDTO;
import com.pqnoje.propostaapp.dto.PropostaResponseDTO;
import com.pqnoje.propostaapp.entity.Proposta;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PropostaMapper {

    @Mapping(target = "usuario.nome", source = "nome")
    @Mapping(target = "usuario.sobrenome", source = "sobrenome")
    @Mapping(target = "usuario.cpf", source = "cpf")
    @Mapping(target = "usuario.telefone", source = "telefone")
    @Mapping(target = "usuario.renda", source = "renda")
    @Mapping(target = "usuario.endereco.cep", source = "cep")
    @Mapping(target = "usuario.endereco.complemento", source = "complemento")
    @Mapping(target = "usuario.endereco.numero", source = "numero")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "aprovada", ignore = true)
    @Mapping(target = "integrada", ignore = true)
    @Mapping(target = "observacao", ignore = true)
    public Proposta convertDTOToProposta(PropostaRequestDTO propostaRequestDTO);

    @Mapping(target = "nome", source = "usuario.nome")
    @Mapping(target = "sobrenome", source = "usuario.sobrenome")
    @Mapping(target = "telefone", source = "usuario.telefone")
    @Mapping(target = "cpf", source = "usuario.cpf")
    @Mapping(target = "renda", source = "usuario.renda")
    @Mapping(target = "cep", source = "usuario.endereco.cep")
    @Mapping(target = "logradouro", source = "usuario.endereco.logradouro")
    @Mapping(target = "complemento", source = "usuario.endereco.complemento")
    @Mapping(target = "bairro", source = "usuario.endereco.bairro")
    @Mapping(target = "localidade", source = "usuario.endereco.localidade")
    @Mapping(target = "uf", source = "usuario.endereco.uf")
    @Mapping(target = "numero", source = "usuario.endereco.numero")
    public PropostaResponseDTO convertEntityToDTO(Proposta proposta);

    Iterable<PropostaResponseDTO> convertListEntityToListDTO(Iterable<Proposta> propostas);
}
