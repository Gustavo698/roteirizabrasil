package com.itb.inf2fm.roterizabrasil.model.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "AvaliacaoGuia", uniqueConstraints = @UniqueConstraint(columnNames = {"usuario_id", "guia_id"}))
public class AvaliacaoGuia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "guia_id", nullable = false)
    private Guia guia;

    @Column(nullable = false)
    private Integer nota;

    @Column(length = 500)
    private String comentario;

    @Column(nullable = false)
    private LocalDateTime data_avaliacao = LocalDateTime.now();

    public AvaliacaoGuia() {}

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }
    public Guia getGuia() { return guia; }
    public void setGuia(Guia guia) { this.guia = guia; }
    public Integer getNota() { return nota; }
    public void setNota(Integer nota) { this.nota = nota; }
    public String getComentario() { return comentario; }
    public void setComentario(String comentario) { this.comentario = comentario; }
    public LocalDateTime getData_avaliacao() { return data_avaliacao; }
    public void setData_avaliacao(LocalDateTime data_avaliacao) { this.data_avaliacao = data_avaliacao; }
}
