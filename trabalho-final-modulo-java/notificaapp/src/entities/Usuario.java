package entities;

import java.util.Date;

public class Usuario {
    private int idUsuario;
    private String nomeUsuario;
    private String numeroCelular;
    private String senhaUsuario;
    private String etniaUsuario;
    private Date dataNascimento;
    private String classeSocial;
    private String generoUsuario;
    private String tipoUsuario;

    public Usuario(int idUsuario, String nomeUsuario, String numeroCelular, String senhaUsuario, String etniaUsuario, Date dataNascimento, String classeSocial, String generoUsuario, String tipoUsuario) {
        this.idUsuario = idUsuario;
        this.nomeUsuario = nomeUsuario;
        this.numeroCelular = numeroCelular;
        this.senhaUsuario = senhaUsuario;
        this.etniaUsuario = etniaUsuario;
        this.dataNascimento = dataNascimento;
        this.classeSocial = classeSocial;
        this.generoUsuario = generoUsuario;
        this.tipoUsuario = tipoUsuario;
    }

    // Método de cadastrar usuario

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getNumeroCelular() {
        return numeroCelular;
    }

    public void setNumeroCelular(String numeroCelular) {
        this.numeroCelular = numeroCelular;
    }

    public String getSenhaUsuario() {
        return senhaUsuario;
    }

    public void setSenhaUsuario(String senhaUsuario) {
        this.senhaUsuario = senhaUsuario;
    }

    public String getEtniaUsuario() {
        return etniaUsuario;
    }

    public void setEtniaUsuario(String etniaUsuario) {
        this.etniaUsuario = etniaUsuario;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getClasseSocial() {
        return classeSocial;
    }

    public void setClasseSocial(String classeSocial) {
        this.classeSocial = classeSocial;
    }

    public String getGeneroUsuario() {
        return generoUsuario;
    }

    public void setGeneroUsuario(String generoUsuario) {
        this.generoUsuario = generoUsuario;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Usuario usuario)) return false;

        return idUsuario == usuario.idUsuario;
    }

    @Override
    public int hashCode() {
        return idUsuario;
    }
}
