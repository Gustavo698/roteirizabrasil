package com.itb.inf2fm.roterizabrasil.model.repository;

import com.itb.inf2fm.roterizabrasil.model.entity.AvaliacaoGuia;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AvaliacaoGuiaRepository extends JpaRepository<AvaliacaoGuia, Integer> {
    List<AvaliacaoGuia> findByGuiaId(Integer guiaId);
    boolean existsByUsuarioIdAndGuiaId(Integer usuarioId, Integer guiaId);
}
