package models.enums;

public enum Situacao {
    ABERTO(1),
    EM_ANALISE(2),
    EM_ANDAMENTO(3),
    RESOLVIDO(4),
    FECHADO(5);

    private final int id;

    Situacao(int id) {
        this.id = id;
    }

    public int getIdSituacao() {
        return id;
    }
}
