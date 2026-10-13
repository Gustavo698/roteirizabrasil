package com.itb.inf2fm.roterizabrasil.service;

import com.itb.inf2fm.roterizabrasil.model.entity.Atracao;
import com.itb.inf2fm.roterizabrasil.model.entity.Destino;
import com.itb.inf2fm.roterizabrasil.model.repository.AtracaoRepository;
import com.itb.inf2fm.roterizabrasil.model.repository.DestinoRepository;
import java.util.Arrays;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class AtracaoService {
    private final AtracaoRepository repo;
    private final DestinoRepository destinoRepo;
    public AtracaoService(AtracaoRepository repo, DestinoRepository destinoRepo) { this.repo = repo; this.destinoRepo = destinoRepo; }
    public Atracao salvar(Atracao a) {
        Destino d = destinoRepo.findById(a.getDestino().getId()).orElseThrow(() -> new RuntimeException("Destino nao encontrado"));
        a.setDestino(d);
        if (!Arrays.asList("ECONOMICO", "MEDIO", "LUXO").contains(a.getFaixa_orcamento())) throw new RuntimeException("Faixa invalida: ECONOMICO, MEDIO ou LUXO");
        return repo.save(a);
    }
    public List<Atracao> listarTodos() { return repo.findAll(); }
    public List<Atracao> listarPorDestino(Integer destinoId) { return repo.findByDestinoId(destinoId); }
    public Atracao buscarPorId(Integer id) { return repo.findById(id).orElseThrow(() -> new RuntimeException("Atracao nao encontrada")); }
    public Atracao atualizar(Integer id, Atracao nova) {
        Atracao a = buscarPorId(id);
        a.setNome(nova.getNome()); a.setDescricao(nova.getDescricao());
        a.setCategoria(nova.getCategoria()); a.setPreco(nova.getPreco());
        a.setFaixa_orcamento(nova.getFaixa_orcamento());
        return repo.save(a);
    }
    public void excluir(Integer id) { repo.deleteById(id); }
}
