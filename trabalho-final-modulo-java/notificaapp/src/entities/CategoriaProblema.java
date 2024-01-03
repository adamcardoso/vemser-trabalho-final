package entities;

import entities.enums.Categoria;

public class CategoriaProblema {
    private int idCategoriaProblema;
    private String nomeCategoriaProblema;
    private Categoria categoria;
    private String descricaoCategoriaProblema;

    public CategoriaProblema(int idCategoriaProblema, String nomeCategoriaProblema, Categoria categoria, String descricaoCategoriaProblema) {
        this.idCategoriaProblema = idCategoriaProblema;
        this.nomeCategoriaProblema = nomeCategoriaProblema;
        this.categoria = categoria;
        this.descricaoCategoriaProblema = descricaoCategoriaProblema;
    }

    public int getIdCategoriaProblema() {
        return idCategoriaProblema;
    }

    public void setIdCategoriaProblema(int idCategoriaProblema) {
        this.idCategoriaProblema = idCategoriaProblema;
    }

    public String getNomeCategoriaProblema() {
        return nomeCategoriaProblema;
    }

    public void setNomeCategoriaProblema(String nomeCategoriaProblema) {
        this.nomeCategoriaProblema = nomeCategoriaProblema;
    }

    public String getDescricaoCategoriaProblema() {
        return descricaoCategoriaProblema;
    }

    public void setDescricaoCategoriaProblema(String descricaoCategoriaProblema) {
        this.descricaoCategoriaProblema = descricaoCategoriaProblema;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
}
