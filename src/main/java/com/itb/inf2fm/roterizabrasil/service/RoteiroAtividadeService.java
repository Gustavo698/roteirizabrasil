package com.itb.inf2fm.roterizabrasil.service;

import com.itb.inf2fm.roterizabrasil.model.entity.*;
import com.itb.inf2fm.roterizabrasil.model.repository.*;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class RoteiroAtividadeService {
    private final RoteiroAtividadeRepository repo;
    private final RoteiroRepository roteiroRepo;
    private final AtracaoRepository atracaoRepo;
    private final GuiaRepository guiaRepo;
    private final RoteiroService roteiroService;
    public RoteiroAtividadeService(RoteiroAtividadeRepository repo, RoteiroRepository roteiroRepo, AtracaoRepository atracaoRepo, GuiaRepository guiaRepo, RoteiroService roteiroService) {
        this.repo = repo; this.roteiroRepo = roteiroRepo; this.atracaoRepo = atracaoRepo; this.guiaRepo = guiaRepo; this.roteiroService = roteiroService;
    }
    public RoteiroAtividade salvar(RoteiroAtividade a) {
        Roteiro r = roteiroRepo.findById(a.getRoteiro().getId()).orElseThrow(() -> new RuntimeException("Roteiro nao encontrado"));
        a.setRoteiro(r);
        if (a.getAtracao() != null && a.getAtracao().getId() != null)
            a.setAtracao(atracaoRepo.findById(a.getAtracao().getId()).orElseThrow(() -> new RuntimeException("Atracao nao encontrada")));
        else a.setAtracao(null);
        if (a.getGuia() != null && a.getGuia().getId() != null)
            a.setGuia(guiaRepo.findById(a.getGuia().getId()).orElseThrow(() -> new RuntimeException("Guia nao encontrado")));
        else a.setGuia(null);
        if (a.getAtracao() == null && a.getGuia() == null) throw new RuntimeException("Atividade precisa de atracao OU guia");
        if (a.getData().isBefore(r.getData_inicio()) || a.getData().isAfter(r.getData_fim())) throw new RuntimeException("Data fora do periodo do roteiro");
        if (a.getValor() == null) {
            BigDecimal v = BigDecimal.ZERO;
            if (a.getAtracao() != null) v = v.add(a.getAtracao().getPreco());
            RoteiroAtividade salva = repo.save(a);
            salva.setValor(v);
            repo.save(salva);
            roteiroService.recalcular(r.getId());
            return salva;
        }
        RoteiroAtividade salva = repo.save(a);
        roteiroService.recalcular(r.getId());
        return salva;
    }
    public List<RoteiroAtividade> listarPorRoteiro(Integer roteiroId) { return repo.findByRoteiroId(roteiroId); }
    public void excluir(Integer id) {
        RoteiroAtividade a = repo.findById(id).orElseThrow(() -> new RuntimeException("Atividade nao encontrada"));
        Integer roteiroId = a.getRoteiro().getId();
        repo.deleteById(id);
        roteiroService.recalcular(roteiroId);
    }
}
