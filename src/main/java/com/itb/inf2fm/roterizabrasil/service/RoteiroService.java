package com.itb.inf2fm.roterizabrasil.service;

import com.itb.inf2fm.roterizabrasil.model.entity.Destino;
import com.itb.inf2fm.roterizabrasil.model.entity.Roteiro;
import com.itb.inf2fm.roterizabrasil.model.entity.Usuario;
import com.itb.inf2fm.roterizabrasil.model.repository.*;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class RoteiroService {
    private final RoteiroRepository repo;
    private final UsuarioRepository usuarioRepo;
    private final DestinoRepository destinoRepo;
    private final RoteiroAtividadeRepository atividadeRepo;
    public RoteiroService(RoteiroRepository repo, UsuarioRepository usuarioRepo, DestinoRepository destinoRepo, RoteiroAtividadeRepository atividadeRepo) {
        this.repo = repo; this.usuarioRepo = usuarioRepo; this.destinoRepo = destinoRepo; this.atividadeRepo = atividadeRepo;
    }
    public Roteiro salvar(Roteiro r) {
        Usuario u = usuarioRepo.findById(r.getUsuario().getId()).orElseThrow(() -> new RuntimeException("Usuario nao encontrado"));
        Destino d = destinoRepo.findById(r.getDestino().getId()).orElseThrow(() -> new RuntimeException("Destino nao encontrado"));
        if (r.getData_fim().isBefore(r.getData_inicio())) throw new RuntimeException("Data fim deve ser apos data inicio");
        r.setUsuario(u); r.setDestino(d); r.setOrcamento_estimado(BigDecimal.ZERO);
        return repo.save(r);
    }
    public List<Roteiro> listarTodos() { return repo.findAll(); }
    public List<Roteiro> listarPorUsuario(Integer usuarioId) { return repo.findByUsuarioId(usuarioId); }
    public Roteiro buscarPorId(Integer id) { return repo.findById(id).orElseThrow(() -> new RuntimeException("Roteiro nao encontrado")); }
    public Roteiro atualizar(Integer id, Roteiro novo) {
        Roteiro r = buscarPorId(id);
        r.setNome(novo.getNome()); r.setData_inicio(novo.getData_inicio()); r.setData_fim(novo.getData_fim());
        return repo.save(r);
    }
    public void excluir(Integer id) { repo.deleteById(id); }
    public void recalcular(Integer roteiroId) {
        Roteiro r = buscarPorId(roteiroId);
        BigDecimal total = atividadeRepo.somarPorRoteiro(roteiroId);
        r.setOrcamento_estimado(total == null ? BigDecimal.ZERO : total);
        repo.save(r);
    }
}
