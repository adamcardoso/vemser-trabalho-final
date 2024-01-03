package entities;

import entities.enums.Categoria;
import entities.enums.Situacao;

import java.time.LocalDateTime;

public class Denuncia{

    private int idDenuncia;
    private String descricao;
    private Localizacao local;
    private Usuario usuario;
    private LocalDateTime dataHora;
    private Situacao statusDenuncia;
    private Categoria categoria;

    public Denuncia(int idDenuncia, String descricao, Localizacao local, Usuario usuario, LocalDateTime dataHora, Situacao statusDenuncia, Categoria categoria) {
        this.idDenuncia = idDenuncia;
        this.descricao = descricao;
        this.local = local;
        this.usuario = usuario;
        this.dataHora = dataHora;
        this.statusDenuncia = statusDenuncia;
        this.categoria = categoria;
    }

    public Denuncia() {}

    public int getIdDenuncia() {
        return idDenuncia;
    }

    public void setIdDenuncia(int idDenuncia) {
        this.idDenuncia = idDenuncia;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Localizacao getLocal() {
        return local;
    }

    public void setLocal(Localizacao local) {
        this.local = local;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }


    public Situacao getStatusDenuncia() {
        return statusDenuncia;
    }

    public void setStatusDenuncia(Situacao statusDenuncia) {
        this.statusDenuncia = statusDenuncia;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
}
