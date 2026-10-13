package com.itb.inf2fm.roterizabrasil.service;

import com.itb.inf2fm.roterizabrasil.model.entity.Destino;
import com.itb.inf2fm.roterizabrasil.model.repository.DestinoRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class DestinoService {
    private final DestinoRepository repo;
    public DestinoService(DestinoRepository repo) { this.repo = repo; }
    public Destino salvar(Destino d) { return repo.save(d); }
    public List<Destino> listarTodos() { return repo.findAll(); }
    public Destino buscarPorId(Integer id) { return repo.findById(id).orElseThrow(() -> new RuntimeException("Destino nao encontrado")); }
    public Destino atualizar(Integer id, Destino novo) {
        Destino d = buscarPorId(id);
        d.setEstado(novo.getEstado()); d.setCidade(novo.getCidade());
        d.setDescricao(novo.getDescricao()); d.setMelhor_epoca(novo.getMelhor_epoca());
        return repo.save(d);
    }
    public void excluir(Integer id) { repo.deleteById(id); }
}
