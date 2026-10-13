package com.itb.inf2fm.roterizabrasil.model.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Destino")
public class Destino {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 2)
    private String estado;

    @Column(nullable = false, length = 100)
    private String cidade;

    @Column(length = 1000)
    private String descricao;

    @Column(length = 100)
    private String melhor_epoca;

    public Destino() {}

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    public String getCidade() { return cidade; }
    public void setCidade(String cidade) { this.cidade = cidade; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public String getMelhor_epoca() { return melhor_epoca; }
    public void setMelhor_epoca(String melhor_epoca) { this.melhor_epoca = melhor_epoca; }
}
