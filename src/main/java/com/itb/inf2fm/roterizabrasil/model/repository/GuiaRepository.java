package com.itb.inf2fm.roterizabrasil.model.repository;

import com.itb.inf2fm.roterizabrasil.model.entity.Guia;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuiaRepository extends JpaRepository<Guia, Integer> {
    List<Guia> findByDestinoId(Integer destinoId);
}
