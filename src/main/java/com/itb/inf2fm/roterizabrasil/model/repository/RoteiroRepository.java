package com.itb.inf2fm.roterizabrasil.model.repository;

import com.itb.inf2fm.roterizabrasil.model.entity.Roteiro;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoteiroRepository extends JpaRepository<Roteiro, Integer> {
    List<Roteiro> findByUsuarioId(Integer usuarioId);
}
