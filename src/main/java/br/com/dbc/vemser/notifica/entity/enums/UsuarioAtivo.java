package br.com.dbc.vemser.notifica.entity.enums;

public enum UsuarioAtivo {
    NAO(0),
    SIM(1);

    private final int id;

    UsuarioAtivo(int id) {
        this.id = id;
    }

    public int getIdUsuarioAtivo() {
        return id;
    }

    public static UsuarioAtivo fromInt(int value) {
        for (UsuarioAtivo tipoUsuario : UsuarioAtivo.values()) {
            if (tipoUsuario.getIdUsuarioAtivo() == value) {
                return tipoUsuario;
            }
        }
        throw new IllegalArgumentException("Valor inválido para a enumeração Usuario Ativo: " + value);
    }
}
