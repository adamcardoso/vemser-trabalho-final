package entities.enums;

public enum ClasseSocial {
    A("0"),
    B("1"),
    C("2"),
    D("3"),
    E("4");

    private final String descricao;
    ClasseSocial(String descricao){
        this.descricao = descricao;
    }

    public String getValor(){
        return descricao;
    }
}