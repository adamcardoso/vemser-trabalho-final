package br.com.dbc.vemser.notifica.entity.enums;

public enum TipoRegistro {
    CRIACAO(0),
    FECHAMENTo(1);
    private final int valor;

    TipoRegistro(int valor) {
        this.valor = valor;
    }

    public int getIdTipoRegistro() {
        return valor;
    }

    public static TipoDenuncia fromInt(int value) {
        for (TipoDenuncia tipoDenuncia : TipoDenuncia.values()) {
            if (tipoDenuncia.getIdTipoDenuncia() == value) {
                return tipoDenuncia;
            }
        }
        throw new IllegalArgumentException("Valor inválido para a enumeração TipoRegistro: " + value);
    }
}
