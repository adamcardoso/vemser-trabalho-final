package br.com.dbc.vemser.notifica.entity.enums;

public enum TipoDenuncia {
    PUBLICA(0),
    ANONIMA(1);
    private final int valor;

    TipoDenuncia(int valor) {
        this.valor = valor;
    }

    public int getIdTipoDenuncia() {
        return valor;
    }

    public static TipoDenuncia fromInt(int value) {
        for (TipoDenuncia tipoDenuncia : TipoDenuncia.values()) {
            if (tipoDenuncia.getIdTipoDenuncia() == value) {
                return tipoDenuncia;
            }
        }
        throw new IllegalArgumentException("Valor inválido para a enumeração TipoDenuncia: " + value);
    }
}
