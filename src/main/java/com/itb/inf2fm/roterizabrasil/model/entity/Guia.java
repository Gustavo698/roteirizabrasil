package com.itb.inf2fm.roterizabrasil.model.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "Guia")
public class Guia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "destino_id", nullable = false)
    private Destino destino;

    @Column(nullable = false, length = 150)
    private String nome;

    @Column(length = 1000)
    private String descricao;

    @Column(length = 20)
    private String telefone;

    @Column(length = 254)
    private String email;

    @Column(length = 150)
    private String idiomas;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal preco_medio = BigDecimal.ZERO;

    @Column(nullable = false)
    private Boolean certificado = false;

    public Guia() {}

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public Destino getDestino() { return destino; }
    public void setDestino(Destino destino) { this.destino = destino; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getIdiomas() { return idiomas; }
    public void setIdiomas(String idiomas) { this.idiomas = idiomas; }
    public BigDecimal getPreco_medio() { return preco_medio; }
    public void setPreco_medio(BigDecimal preco_medio) { this.preco_medio = preco_medio; }
    public Boolean getCertificado() { return certificado; }
    public void setCertificado(Boolean certificado) { this.certificado = certificado; }
}
