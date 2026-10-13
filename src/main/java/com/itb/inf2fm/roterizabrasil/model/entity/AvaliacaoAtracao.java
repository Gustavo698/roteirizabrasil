package com.itb.inf2fm.roterizabrasil.model.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "AvaliacaoAtracao", uniqueConstraints = @UniqueConstraint(columnNames = {"usuario_id", "atracao_id"}))
public class AvaliacaoAtracao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "atracao_id", nullable = false)
    private Atracao atracao;

    @Column(nullable = false)
    private Integer nota;

    @Column(length = 500)
    private String comentario;

    @Column(nullable = false)
    private LocalDateTime data_avaliacao = LocalDateTime.now();

    public AvaliacaoAtracao() {}

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }
    public Atracao getAtracao() { return atracao; }
    public void setAtracao(Atracao atracao) { this.atracao = atracao; }
    public Integer getNota() { return nota; }
    public void setNota(Integer nota) { this.nota = nota; }
    public String getComentario() { return comentario; }
    public void setComentario(String comentario) { this.comentario = comentario; }
    public LocalDateTime getData_avaliacao() { return data_avaliacao; }
    public void setData_avaliacao(LocalDateTime data_avaliacao) { this.data_avaliacao = data_avaliacao; }
}
