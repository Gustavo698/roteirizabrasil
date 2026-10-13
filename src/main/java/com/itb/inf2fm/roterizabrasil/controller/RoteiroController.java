package com.itb.inf2fm.roterizabrasil.controller;

import com.itb.inf2fm.roterizabrasil.model.entity.Roteiro;
import com.itb.inf2fm.roterizabrasil.model.entity.RoteiroAtividade;
import com.itb.inf2fm.roterizabrasil.service.RoteiroAtividadeService;
import com.itb.inf2fm.roterizabrasil.service.RoteiroService;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/roteiros")
public class RoteiroController {
    private final RoteiroService service;
    private final RoteiroAtividadeService atividadeService;
    public RoteiroController(RoteiroService service, RoteiroAtividadeService atividadeService) { this.service = service; this.atividadeService = atividadeService; }
    @PostMapping
    public ResponseEntity<?> criar(@RequestBody Roteiro r) {
        try { return ResponseEntity.status(201).body(service.salvar(r)); }
        catch (RuntimeException e) { return ResponseEntity.badRequest().body(e.getMessage()); }
    }
    @GetMapping
    public List<Roteiro> listar(@RequestParam(required = false) Integer usuarioId) {
        if (usuarioId != null) return service.listarPorUsuario(usuarioId);
        return service.listarTodos();
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> buscar(@PathVariable Integer id) {
        try { return ResponseEntity.ok(service.buscarPorId(id)); }
        catch (RuntimeException e) { return ResponseEntity.notFound().build(); }
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Integer id, @RequestBody Roteiro r) {
        try { return ResponseEntity.ok(service.atualizar(id, r)); }
        catch (RuntimeException e) { return ResponseEntity.badRequest().body(e.getMessage()); }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Integer id) { service.excluir(id); return ResponseEntity.noContent().build(); }
    @PostMapping("/{id}/atividades")
    public ResponseEntity<?> adicionarAtividade(@PathVariable Integer id, @RequestBody RoteiroAtividade a) {
        try {
            Roteiro r = service.buscarPorId(id);
            a.setRoteiro(r);
            return ResponseEntity.status(201).body(atividadeService.salvar(a));
        } catch (RuntimeException e) { return ResponseEntity.badRequest().body(e.getMessage()); }
    }
    @GetMapping("/{id}/atividades")
    public List<RoteiroAtividade> listarAtividades(@PathVariable Integer id) { return atividadeService.listarPorRoteiro(id); }
    @DeleteMapping("/atividades/{atividadeId}")
    public ResponseEntity<?> excluirAtividade(@PathVariable Integer atividadeId) {
        try { atividadeService.excluir(atividadeId); return ResponseEntity.noContent().build(); }
        catch (RuntimeException e) { return ResponseEntity.notFound().build(); }
    }
}
