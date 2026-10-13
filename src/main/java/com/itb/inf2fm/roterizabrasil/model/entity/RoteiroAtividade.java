package com.itb.inf2fm.roterizabrasil.model.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "RoteiroAtividade")
public class RoteiroAtividade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "roteiro_id", nullable = false)
    private Roteiro roteiro;

    @ManyToOne
    @JoinColumn(name = "atracao_id")
    private Atracao atracao;

    @ManyToOne
    @JoinColumn(name = "guia_id")
    private Guia guia;

    @Column(nullable = false)
    private LocalDate data;

    private LocalTime horario;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal valor = BigDecimal.ZERO;

    public RoteiroAtividade() {}

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public Roteiro getRoteiro() { return roteiro; }
    public void setRoteiro(Roteiro roteiro) { this.roteiro = roteiro; }
    public Atracao getAtracao() { return atracao; }
    public void setAtracao(Atracao atracao) { this.atracao = atracao; }
    public Guia getGuia() { return guia; }
    public void setGuia(Guia guia) { this.guia = guia; }
    public LocalDate getData() { return data; }
    public void setData(LocalDate data) { this.data = data; }
    public LocalTime getHorario() { return horario; }
    public void setHorario(LocalTime horario) { this.horario = horario; }
    public BigDecimal getValor() { return valor; }
    public void setValor(BigDecimal valor) { this.valor = valor; }
}
