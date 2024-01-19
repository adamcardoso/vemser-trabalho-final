package models.enums;

public enum StatusDenuncia {
    ABERTO(1),
    EM_ANALISE(2),
    EM_ANDAMENTO(3),
    RESOLVIDO(4),
    FECHADO(5);

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
