package com.itb.inf2fm.roterizabrasil.service;

import com.itb.inf2fm.roterizabrasil.model.entity.*;
import com.itb.inf2fm.roterizabrasil.model.repository.*;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class AvaliacaoService {
    private final AvaliacaoGuiaRepository guiaRepo;
    private final AvaliacaoAtracaoRepository atracaoRepo;
    private final UsuarioRepository usuarioRepo;
    private final GuiaRepository gRepo;
    private final AtracaoRepository aRepo;
    public AvaliacaoService(AvaliacaoGuiaRepository guiaRepo, AvaliacaoAtracaoRepository atracaoRepo, UsuarioRepository usuarioRepo, GuiaRepository gRepo, AtracaoRepository aRepo) {
        this.guiaRepo = guiaRepo; this.atracaoRepo = atracaoRepo; this.usuarioRepo = usuarioRepo; this.gRepo = gRepo; this.aRepo = aRepo;
    }
    private void validarNota(Integer nota) { if (nota == null || nota < 1 || nota > 5) throw new RuntimeException("Nota deve ser de 1 a 5"); }
    public AvaliacaoGuia avaliarGuia(AvaliacaoGuia a) {
        validarNota(a.getNota());
        Usuario u = usuarioRepo.findById(a.getUsuario().getId()).orElseThrow(() -> new RuntimeException("Usuario nao encontrado"));
        Guia g = gRepo.findById(a.getGuia().getId()).orElseThrow(() -> new RuntimeException("Guia nao encontrado"));
        if (guiaRepo.existsByUsuarioIdAndGuiaId(u.getId(), g.getId())) throw new RuntimeException("Voce ja avaliou este guia");
        a.setUsuario(u); a.setGuia(g);
        return guiaRepo.save(a);
    }
    public AvaliacaoAtracao avaliarAtracao(AvaliacaoAtracao a) {
        validarNota(a.getNota());
        Usuario u = usuarioRepo.findById(a.getUsuario().getId()).orElseThrow(() -> new RuntimeException("Usuario nao encontrado"));
        Atracao at = aRepo.findById(a.getAtracao().getId()).orElseThrow(() -> new RuntimeException("Atracao nao encontrada"));
        if (atracaoRepo.existsByUsuarioIdAndAtracaoId(u.getId(), at.getId())) throw new RuntimeException("Voce ja avaliou esta atracao");
        a.setUsuario(u); a.setAtracao(at);
        return atracaoRepo.save(a);
    }
    public List<AvaliacaoGuia> listarPorGuia(Integer guiaId) { return guiaRepo.findByGuiaId(guiaId); }
    public List<AvaliacaoAtracao> listarPorAtracao(Integer atracaoId) { return atracaoRepo.findByAtracaoId(atracaoId); }
}
