package com.itb.inf2fm.roterizabrasil.controller;

import com.itb.inf2fm.roterizabrasil.model.entity.Destino;
import com.itb.inf2fm.roterizabrasil.service.DestinoService;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/destinos")
public class DestinoController {
    private final DestinoService service;
    public DestinoController(DestinoService service) { this.service = service; }
    @PostMapping
    public ResponseEntity<Destino> criar(@RequestBody Destino d) { return ResponseEntity.status(201).body(service.salvar(d)); }
    @GetMapping
    public List<Destino> listar() { return service.listarTodos(); }
    @GetMapping("/{id}")
    public ResponseEntity<?> buscar(@PathVariable Integer id) {
        try { return ResponseEntity.ok(service.buscarPorId(id)); }
        catch (RuntimeException e) { return ResponseEntity.notFound().build(); }
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Integer id, @RequestBody Destino d) {
        try { return ResponseEntity.ok(service.atualizar(id, d)); }
        catch (RuntimeException e) { return ResponseEntity.notFound().build(); }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Integer id) { service.excluir(id); return ResponseEntity.noContent().build(); }
}
