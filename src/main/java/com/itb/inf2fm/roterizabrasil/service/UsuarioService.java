package com.itb.inf2fm.roterizabrasil.service;

import com.itb.inf2fm.roterizabrasil.model.entity.Usuario;
import com.itb.inf2fm.roterizabrasil.model.repository.UsuarioRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    private final UsuarioRepository repo;
    public UsuarioService(UsuarioRepository repo) { this.repo = repo; }
    public Usuario salvar(Usuario u) {
        if (repo.findByEmail(u.getEmail()).isPresent()) throw new RuntimeException("E-mail ja cadastrado");
        if (u.getNivelAcesso() == null) u.setNivelAcesso("USER");
        u.setStatusUsuario("ATIVO");
        return repo.save(u);
    }
    public List<Usuario> listarTodos() { return repo.findAll(); }
    public Usuario buscarPorId(Integer id) { return repo.findById(id).orElseThrow(() -> new RuntimeException("Usuario nao encontrado")); }
    public Usuario atualizar(Integer id, Usuario novo) {
        Usuario u = buscarPorId(id);
        u.setNome(novo.getNome()); u.setTelefone(novo.getTelefone());
        return repo.save(u);
    }
    public void excluir(Integer id) { Usuario u = buscarPorId(id); u.setStatusUsuario("INATIVO"); repo.save(u); }
}
