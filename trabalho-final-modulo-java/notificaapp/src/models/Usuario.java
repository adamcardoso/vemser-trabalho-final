package models;

import models.enums.ClasseSocial;
import models.enums.Etnia;
import models.enums.Genero;
import models.enums.TipoUsuario;

import java.time.LocalDate;

public class Usuario {
    private int idUsuario;
    private String nomeUsuario;
    private String numeroCelular;
    private String senhaUsuario;
    private Etnia etniaUsuario;
    private LocalDate dataNascimento;
    private ClasseSocial classeSocial;
    private Genero generoUsuario;
    private TipoUsuario tipoUsuario;

    public Usuario(int idUsuario, String nomeUsuario, String numeroCelular, String senhaUsuario, Etnia etniaUsuario, LocalDate dataNascimento, ClasseSocial classeSocial, Genero generoUsuario, TipoUsuario tipoUsuario) {
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

    public Usuario() {
    }

    public Usuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public Usuario(int idUsuario, String nomeUsuario) {
        this.idUsuario = idUsuario;
        this.nomeUsuario = nomeUsuario;
    }

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

    public Etnia getEtniaUsuario() {
        return etniaUsuario;
    }

    public void setEtniaUsuario(Etnia etniaUsuario) {
        this.etniaUsuario = etniaUsuario;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public ClasseSocial getClasseSocial() {
        return classeSocial;
    }

    public void setClasseSocial(ClasseSocial classeSocial) {
        this.classeSocial = classeSocial;
    }

    public Genero getGeneroUsuario() {
        return generoUsuario;
    }

    public void setGeneroUsuario(Genero generoUsuario) {
        this.generoUsuario = generoUsuario;
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
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

    @Override
    public String toString() {
        return "Usuario{" +
                "idUsuario=" + idUsuario +
                ", nomeUsuario='" + nomeUsuario + '\'' +
                ", numeroCelular='" + numeroCelular + '\'' +
                ", senhaUsuario='" + senhaUsuario + '\'' +
                ", etniaUsuario=" + etniaUsuario +
                ", dataNascimento=" + dataNascimento +
                ", classeSocial=" + classeSocial +
                ", generoUsuario=" + generoUsuario +
                ", tipoUsuario=" + tipoUsuario +
                '}';
    }
}