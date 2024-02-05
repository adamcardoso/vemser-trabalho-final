package br.com.dbc.vemser.notifica.entity.enums;

public enum Categoria {
    AGUA_POTAVEL(0),
    SANEAMENTO_BASICO(1),
    GESTAO_RESIDUOS(2),
    EDUCACAO_HIGIENE(3);

    private final Integer valor;

    Categoria(Integer valor) {
        this.valor = valor;
    }

    public Integer getIdCategoria() {
        return valor;
    }

    public static Categoria fromInt(Integer value) {
        for (Categoria categoria : Categoria.values()) {
            if (categoria.getIdCategoria() == value) {
                return categoria;
            }
        }
        throw new IllegalArgumentException("Valor inválido para a enumeração Categoria: " + value);
    }
}
