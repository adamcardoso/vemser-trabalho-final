package entities;

import entities.enums.Situacao;
import interfaces.IDenuncia;

import java.time.LocalDateTime;

public class Denuncia implements IDenuncia {
    private int idDenuncia;
    private String descricao;
    private Localizacao local;
    private Usuario usuario;
    private LocalDateTime dataHora;
    private String statusDenuncia;
    private Situacao situacao;

    public Denuncia(int idDenuncia, String descricao, Localizacao local, Usuario usuario, LocalDateTime dataHora, String statusDenuncia, Situacao situacao) {
        this.idDenuncia = idDenuncia;
        this.descricao = descricao;
        this.local = local;
        this.usuario = usuario;
        this.dataHora = dataHora;
        this.statusDenuncia = statusDenuncia;
        this.situacao = situacao;
    }

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

    public String getStatusDenuncia() {
        return statusDenuncia;
    }

    public void setStatusDenuncia(String statusDenuncia) {
        this.statusDenuncia = statusDenuncia;
    }

    public Situacao getSituacao() {
        return situacao;
    }

    public void setSituacao(Situacao situacao) {
        this.situacao = situacao;
    }

    @Override
    public void cadastrarDenuncia(Denuncia denuncia) {
        System.out.println("Cadastrar denuncia");
    }

    @Override
    public void editarDenuncia(int idDenuncia, Denuncia novaDenuncia) {
        System.out.println("Editar denuncia");
    }

    @Override
    public void excluirDenuncia(int idDenuncia) {
        System.out.println("Excluir denuncia!");
    }

    @Override
    public boolean validarDenuncia(int idDenuncia) {
        return false;
    }
}
