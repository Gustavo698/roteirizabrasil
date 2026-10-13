package com.itb.inf2fm.roterizabrasil.controller;

import com.itb.inf2fm.roterizabrasil.model.entity.AvaliacaoAtracao;
import com.itb.inf2fm.roterizabrasil.model.entity.AvaliacaoGuia;
import com.itb.inf2fm.roterizabrasil.service.AvaliacaoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/avaliacoes")
public class AvaliacaoController {
    private final AvaliacaoService service;
    public AvaliacaoController(AvaliacaoService service) { this.service = service; }
    @PostMapping("/guias")
    public ResponseEntity<?> avaliarGuia(@RequestBody AvaliacaoGuia a) {
        try { return ResponseEntity.status(201).body(service.avaliarGuia(a)); }
        catch (RuntimeException e) { return ResponseEntity.badRequest().body(e.getMessage()); }
    }
    @PostMapping("/atracoes")
    public ResponseEntity<?> avaliarAtracao(@RequestBody AvaliacaoAtracao a) {
        try { return ResponseEntity.status(201).body(service.avaliarAtracao(a)); }
        catch (RuntimeException e) { return ResponseEntity.badRequest().body(e.getMessage()); }
    }
    @GetMapping("/guia/{guiaId}")
    public ResponseEntity<?> porGuia(@PathVariable Integer guiaId) { return ResponseEntity.ok(service.listarPorGuia(guiaId)); }
    @GetMapping("/atracao/{atracaoId}")
    public ResponseEntity<?> porAtracao(@PathVariable Integer atracaoId) { return ResponseEntity.ok(service.listarPorAtracao(atracaoId)); }
}
