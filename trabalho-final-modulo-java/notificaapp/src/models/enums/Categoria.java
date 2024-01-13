package models.enums;

public enum Categoria {
    AGUA_POTAVEL(1),
    SANEAMENTO_BASICO(2),
    GESTAO_RESIDUOS(3),
    EDUCACAO_HIGIENE(4);

    private final int valor;

    Categoria(int valor) {
        this.valor = valor;
    }

    public int getIdCategoria() {
        return valor;
    }

    public static Categoria fromInt(int value) {
        for (Categoria categoria : Categoria.values()) {
            if (categoria.getIdCategoria() == value) {
                return categoria;
            }
        }
        throw new IllegalArgumentException("Valor inválido para a enumeração Categoria: " + value);
    }
}
