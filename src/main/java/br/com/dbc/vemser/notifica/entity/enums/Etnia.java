package br.com.dbc.vemser.notifica.entity.enums;

public enum Etnia {
    PRETO(0),
    PARDO(1),
    BRANCO(2),
    INDIGENA(3),
    AMARELO(4);

    private final int valor;

    Etnia(int valor) {
        this.valor = valor;
    }

    public int getIdEtnia() {
        return valor;
    }

    public static Etnia fromInt(int value) {
        for (Etnia etnia : Etnia.values()) {
            if (etnia.getIdEtnia() == value) {
                return etnia;
            }
        }
        throw new IllegalArgumentException("Valor inválido para a enumeração Etnia: " + value);
    }
}
