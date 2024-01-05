package entities.enums;

public enum Etnia {
    PRETO("0"),
    PARDO("1"),
    BRANCO("2"),
    INDIGENA("3"),
    AMARELO("4");

    private final String descricao;
    Etnia(String descricao){
        this.descricao = descricao;
    }
    public String getValor(){
        return descricao;
    }
}