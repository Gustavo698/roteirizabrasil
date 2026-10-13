package com.itb.inf2fm.roterizabrasil.controller;

import com.itb.inf2fm.roterizabrasil.model.entity.Usuario;
import com.itb.inf2fm.roterizabrasil.service.UsuarioService;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/usuarios")
public class UsuarioController {
    private final UsuarioService service;
    public UsuarioController(UsuarioService service) { this.service = service; }
    @PostMapping
    public ResponseEntity<?> criar(@RequestBody Usuario u) {
        try { return ResponseEntity.status(201).body(service.salvar(u)); }
        catch (RuntimeException e) { return ResponseEntity.badRequest().body(e.getMessage()); }
    }
    @GetMapping
    public List<Usuario> listar() { return service.listarTodos(); }
    @GetMapping("/{id}")
    public ResponseEntity<?> buscar(@PathVariable Integer id) {
        try { return ResponseEntity.ok(service.buscarPorId(id)); }
        catch (RuntimeException e) { return ResponseEntity.notFound().build(); }
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Integer id, @RequestBody Usuario u) {
        try { return ResponseEntity.ok(service.atualizar(id, u)); }
        catch (RuntimeException e) { return ResponseEntity.notFound().build(); }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Integer id) { service.excluir(id); return ResponseEntity.noContent().build(); }
}
