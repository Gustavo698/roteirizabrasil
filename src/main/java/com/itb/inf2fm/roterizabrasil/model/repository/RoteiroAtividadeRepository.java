package com.itb.inf2fm.roterizabrasil.model.repository;

import com.itb.inf2fm.roterizabrasil.model.entity.RoteiroAtividade;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RoteiroAtividadeRepository extends JpaRepository<RoteiroAtividade, Integer> {
    List<RoteiroAtividade> findByRoteiroId(Integer roteiroId);
    @Query("SELECT COALESCE(SUM(a.valor), 0) FROM RoteiroAtividade a WHERE a.roteiro.id = :roteiroId")
    BigDecimal somarPorRoteiro(@Param("roteiroId") Integer roteiroId);
}
