package models.enums;


public enum Genero {
    MASCULINO(0),
    FEMININO(1),
    OUTRO(2);

    private final int valor;

    Genero(int valor) {
        this.valor = valor;
    }

    public int getIdGenero() {
        return valor;
    }

    public static Genero fromInt(int value) {
        for (Genero genero : Genero.values()) {
            if (genero.getIdGenero() == value) {
                return genero;
            }
        }
        throw new IllegalArgumentException("Valor inválido para a enumeração Genero: " + value);
    }
}

