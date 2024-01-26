package br.com.dbc.vemser.notifica.entity;

import br.com.dbc.vemser.notifica.entity.enums.Categoria;
import br.com.dbc.vemser.notifica.entity.enums.StatusDenuncia;
import br.com.dbc.vemser.notifica.entity.enums.TipoDenuncia;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Denuncia{
    private int idDenuncia;
    private String descricao;
    private String titulo;
    private Localizacao local;
    private LocalDateTime dataHora;
    private StatusDenuncia statusDenuncia;
    private Categoria categoria;
    private int curtidas;
    private final List<String> comentarios = new ArrayList<>();
    private TipoDenuncia tipoDenuncia;
    private int idUsuario;

    public Denuncia(int idDenuncia, String descricao, String titulo, StatusDenuncia statusDenuncia, Categoria categoria, TipoDenuncia tipoDenuncia, int idUsuario) {
        this.idDenuncia = idDenuncia;
        this.descricao = descricao;
        this.titulo = titulo;
        this.statusDenuncia = statusDenuncia;
        this.categoria = categoria;
        this.tipoDenuncia = tipoDenuncia;
        this.idUsuario = idUsuario;
    }



}
