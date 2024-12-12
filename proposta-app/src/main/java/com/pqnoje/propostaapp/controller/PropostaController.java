package com.pqnoje.propostaapp.controller;

import com.pqnoje.propostaapp.dto.PropostaRequestDTO;
import com.pqnoje.propostaapp.dto.PropostaResponseDTO;
import com.pqnoje.propostaapp.service.PropostaService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/proposta")
public class PropostaController {

    private PropostaService propostaService;

    @PostMapping
    public ResponseEntity<PropostaResponseDTO> criar(@RequestBody PropostaRequestDTO requestDTO) {
        PropostaResponseDTO response = this.propostaService.criar(requestDTO);
        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(response.getId())
                .toUri())
                .body(response);
    }

    @GetMapping
    public ResponseEntity<Iterable<PropostaResponseDTO>> obterProposta() {
        return ResponseEntity.ok(this.propostaService.obterProposta());
    }
}
