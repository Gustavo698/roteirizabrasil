package com.itb.inf2fm.roterizabrasil.model.repository;

import com.itb.inf2fm.roterizabrasil.model.entity.AvaliacaoAtracao;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AvaliacaoAtracaoRepository extends JpaRepository<AvaliacaoAtracao, Integer> {
    List<AvaliacaoAtracao> findByAtracaoId(Integer atracaoId);
    boolean existsByUsuarioIdAndAtracaoId(Integer usuarioId, Integer atracaoId);
}
