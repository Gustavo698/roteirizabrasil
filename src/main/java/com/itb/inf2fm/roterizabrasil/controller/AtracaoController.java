package com.itb.inf2fm.roterizabrasil.controller;

import com.itb.inf2fm.roterizabrasil.model.entity.Atracao;
import com.itb.inf2fm.roterizabrasil.service.AtracaoService;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/atracoes")
public class AtracaoController {
    private final AtracaoService service;
    public AtracaoController(AtracaoService service) { this.service = service; }
    @PostMapping
    public ResponseEntity<?> criar(@RequestBody Atracao a) {
        try { return ResponseEntity.status(201).body(service.salvar(a)); }
        catch (RuntimeException e) { return ResponseEntity.badRequest().body(e.getMessage()); }
    }
    @GetMapping
    public List<Atracao> listar(@RequestParam(required = false) Integer destinoId) {
        if (destinoId != null) return service.listarPorDestino(destinoId);
        return service.listarTodos();
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> buscar(@PathVariable Integer id) {
        try { return ResponseEntity.ok(service.buscarPorId(id)); }
        catch (RuntimeException e) { return ResponseEntity.notFound().build(); }
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Integer id, @RequestBody Atracao a) {
        try { return ResponseEntity.ok(service.atualizar(id, a)); }
        catch (RuntimeException e) { return ResponseEntity.notFound().build(); }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Integer id) { service.excluir(id); return ResponseEntity.noContent().build(); }
}
