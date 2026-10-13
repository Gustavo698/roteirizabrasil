package com.itb.inf2fm.roterizabrasil.service;

import com.itb.inf2fm.roterizabrasil.model.entity.Destino;
import com.itb.inf2fm.roterizabrasil.model.entity.Guia;
import com.itb.inf2fm.roterizabrasil.model.repository.DestinoRepository;
import com.itb.inf2fm.roterizabrasil.model.repository.GuiaRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class GuiaService {
    private final GuiaRepository repo;
    private final DestinoRepository destinoRepo;
    public GuiaService(GuiaRepository repo, DestinoRepository destinoRepo) { this.repo = repo; this.destinoRepo = destinoRepo; }
    public Guia salvar(Guia g) {
        Destino d = destinoRepo.findById(g.getDestino().getId()).orElseThrow(() -> new RuntimeException("Destino nao encontrado"));
        g.setDestino(d);
        return repo.save(g);
    }
    public List<Guia> listarTodos() { return repo.findAll(); }
    public List<Guia> listarPorDestino(Integer destinoId) { return repo.findByDestinoId(destinoId); }
    public Guia buscarPorId(Integer id) { return repo.findById(id).orElseThrow(() -> new RuntimeException("Guia nao encontrado")); }
    public Guia atualizar(Integer id, Guia novo) {
        Guia g = buscarPorId(id);
        g.setNome(novo.getNome()); g.setDescricao(novo.getDescricao());
        g.setTelefone(novo.getTelefone()); g.setEmail(novo.getEmail());
        g.setIdiomas(novo.getIdiomas()); g.setPreco_medio(novo.getPreco_medio());
        g.setCertificado(novo.getCertificado());
        return repo.save(g);
    }
    public void excluir(Integer id) { repo.deleteById(id); }
}
