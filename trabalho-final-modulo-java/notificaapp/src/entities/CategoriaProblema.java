package entities;

public class CategoriaProblema {
    private int idCategoriaProblema;
    private String nomeCategoriaProblema;
    private String descricaoCategoriaProblema;

    public CategoriaProblema(int idCategoriaProblema, String nomeCategoriaProblema, String descricaoCategoriaProblema) {
        this.idCategoriaProblema = idCategoriaProblema;
        this.nomeCategoriaProblema = nomeCategoriaProblema;
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
}
