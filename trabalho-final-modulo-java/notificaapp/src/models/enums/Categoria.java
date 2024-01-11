package models.enums;

public enum Categoria {
    AGUA_POTAVEL(1),
    SANEAMENTO_BASICO(2),
    GESTAO_RESIDUOS(3),
    EDUCACAO_HIGIENE(4);

    private final int id;

    Categoria(int id) {
        this.id = id;
    }

    public int getIdCategoria() {
        return id;
    }
}
