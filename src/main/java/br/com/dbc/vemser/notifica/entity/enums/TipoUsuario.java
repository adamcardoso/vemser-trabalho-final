package br.com.dbc.vemser.notifica.entity.enums;

public enum TipoUsuario {
    ADMIN(0),
    COMUM(1);

    private final int id;

    TipoUsuario(int id) {
        this.id = id;
    }

    public int getIdTipoUsuario() {
        return id;
    }

    public static TipoUsuario fromInt(int value) {
        for (TipoUsuario tipoUsuario : TipoUsuario.values()) {
            if (tipoUsuario.getIdTipoUsuario() == value) {
                return tipoUsuario;
            }
        }
        throw new IllegalArgumentException("Valor inválido para a enumeração tipo Usuario: " + value);
    }
}
