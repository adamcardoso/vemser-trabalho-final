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
}

