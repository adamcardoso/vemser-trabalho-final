package br.com.dbc.vemser.notifica.entity.enums;

public enum StatusDenuncia {
    ABERTO(0),
    EM_ANALISE(1),
    EM_ANDAMENTO(2),
    RESOLVIDO(3),
    FECHADO(4);

    private final int valor;

    StatusDenuncia(int valor) {
        this.valor = valor;
    }

    public int getIdStatusDenuncia() {
        return valor;
    }

    public static StatusDenuncia fromInt(int value) {
        for (StatusDenuncia statusDenuncia : StatusDenuncia.values()) {
            if (statusDenuncia.getIdStatusDenuncia() == value) {
                return statusDenuncia;
            }
        }
        throw new IllegalArgumentException("Valor inválido para a enumeração Status da Denuncia: " + value);
    }
}
