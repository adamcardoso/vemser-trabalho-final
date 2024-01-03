package entities;

import interfaces.IDenuncia;

import java.time.LocalDateTime;

public class Denuncia implements IDenuncia {
    private int idDenuncia;
    private String descricao;
    private Localizacao local;
    private Usuario usuario;
    private LocalDateTime dataHora;
    private String statusDenuncia;

    public Denuncia(int idDenuncia, String descricao, Localizacao local, Usuario usuario, LocalDateTime dataHora, String statusDenuncia) {
        this.idDenuncia = idDenuncia;
        this.descricao = descricao;
        this.local = local;
        this.usuario = usuario;
        this.dataHora = dataHora;
        this.statusDenuncia = statusDenuncia;
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

    @Override
    public void cadastrarDenuncia(Denuncia denuncia) {
        System.out.println("Cadastrar denuncia");
    }

    @Override
    public void editarDenuncia(int idDenuncia, Denuncia novaDenuncia) {
        System.out.println("Editar denuncia");
    }

    @Override
    public boolean validarDenuncia(int idDenuncia) {
        return false;
    }

    @Override
    public void excluirDenuncia(int idDenuncia) {
        System.out.println("Excluir denuncia");
    }
}
