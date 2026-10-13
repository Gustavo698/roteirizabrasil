package com.itb.inf2fm.roterizabrasil.model.repository;

import com.itb.inf2fm.roterizabrasil.model.entity.Atracao;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AtracaoRepository extends JpaRepository<Atracao, Integer> {
    List<Atracao> findByDestinoId(Integer destinoId);
}
