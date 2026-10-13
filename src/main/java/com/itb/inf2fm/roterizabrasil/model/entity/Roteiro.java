package com.itb.inf2fm.roterizabrasil.model.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "Roteiro")
public class Roteiro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "destino_id", nullable = false)
    private Destino destino;

    @Column(nullable = false, length = 150)
    private String nome;

    @Column(nullable = false)
    private LocalDate data_inicio;

    @Column(nullable = false)
    private LocalDate data_fim;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal orcamento_estimado = BigDecimal.ZERO;

    public Roteiro() {}

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }
    public Destino getDestino() { return destino; }
    public void setDestino(Destino destino) { this.destino = destino; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public LocalDate getData_inicio() { return data_inicio; }
    public void setData_inicio(LocalDate data_inicio) { this.data_inicio = data_inicio; }
    public LocalDate getData_fim() { return data_fim; }
    public void setData_fim(LocalDate data_fim) { this.data_fim = data_fim; }
    public BigDecimal getOrcamento_estimado() { return orcamento_estimado; }
    public void setOrcamento_estimado(BigDecimal orcamento_estimado) { this.orcamento_estimado = orcamento_estimado; }
}
