package models.enums;

public enum ClasseSocial {
    A(0),
    B(1),
    C(2),
    D(3),
    E(4);

    private final int valor;

    ClasseSocial(int valor) {
        this.valor = valor;
    }

    public int getIdClasseSocial() {
        return valor;
    }

    public static ClasseSocial fromInt(int value) {
        for (ClasseSocial classeSocial : ClasseSocial.values()) {
            if (classeSocial.getIdClasseSocial() == value) {
                return classeSocial;
            }
        }
        throw new IllegalArgumentException("Valor inválido para a enumeração Classe Social: " + value);
    }
}
