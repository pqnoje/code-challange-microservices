package com.pqnoje.propostaapp.service;


import com.pqnoje.propostaapp.dto.PropostaRequestDTO;
import com.pqnoje.propostaapp.dto.PropostaResponseDTO;
import com.pqnoje.propostaapp.entity.Endereco;
import com.pqnoje.propostaapp.entity.Proposta;
import com.pqnoje.propostaapp.mapper.EnderecoMapper;
import com.pqnoje.propostaapp.mapper.PropostaMapper;
import com.pqnoje.propostaapp.repository.EnderecoRepository;
import com.pqnoje.propostaapp.repository.PropostaRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PropostaService {

    private PropostaMapper propostaMapper;

    private EnderecoMapper enderecoMapper;

    private PropostaRepository propostaRepository;

    private NotificacaoRabbitMQService notificacaoRabbitMQService;

    private EnderecoService enderecoService;

    private String exchange;

    private EnderecoRepository enderecoRepository;

    public PropostaService(PropostaMapper propostaMapper, EnderecoMapper enderecoMapper, PropostaRepository propostaRepository, NotificacaoRabbitMQService notificacaoRabbitMQService, EnderecoService enderecoService, @Value("${rabbitmq.propostapendente.exchange}")String exchange, EnderecoRepository enderecoRepository) {
        this.propostaMapper = propostaMapper;
        this.enderecoMapper = enderecoMapper;
        this.propostaRepository = propostaRepository;
        this.notificacaoRabbitMQService = notificacaoRabbitMQService;
        this.enderecoService = enderecoService;
        this.exchange = exchange;
        this.enderecoRepository = enderecoRepository;
    }

    public PropostaResponseDTO criar(PropostaRequestDTO requestDTO){
        Proposta proposta = this.propostaMapper.convertDTOToProposta(requestDTO);
        proposta.setIntegrada(true);
        Endereco endereco = this.enderecoMapper.convertDTOToEndereco(this.enderecoService.buscarEnderecoPorCEP(proposta.getUsuario().getEndereco().getCep()));
        proposta.getUsuario().setEndereco(endereco);
        endereco.setNumero(requestDTO.getNumero());
        endereco.setComplemento(requestDTO.getComplemento());
        this.propostaRepository.save(proposta);
        endereco.setUsuario(proposta.getUsuario());
        this.enderecoRepository.save(endereco);
        this.notificarRabbitMQ(proposta);
        return this.propostaMapper.convertEntityToDTO(proposta);
    }

    private void notificarRabbitMQ(Proposta proposta){
        try{
            this.notificacaoRabbitMQService.notificar(proposta, exchange);
        }catch(RuntimeException ex){
            proposta.setIntegrada(false);
            this.propostaRepository.save(proposta);
        }
    }

    public Iterable<PropostaResponseDTO> obterProposta() {
        Iterable<Proposta> propostas = this.propostaRepository.findAll();
        return this.propostaMapper.convertListEntityToListDTO(propostas);
    }
}
