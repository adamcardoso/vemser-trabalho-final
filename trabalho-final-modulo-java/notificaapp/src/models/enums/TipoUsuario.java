package models.enums;

public enum TipoUsuario {
    ADMIN(1),
    INDIVIDUAL(2);

    private final int id;

    TipoUsuario(int id) {
        this.id = id;
    }

    public int getIdTipoUsuario() {
        return id;
    }
}
