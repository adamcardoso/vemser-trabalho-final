package entities.enums;

public enum Genero {
    MASCULINO("0"),
    FEMININO("1"),
    OUTRO("2");

    private final String descricao;
    Genero(String descricao){
        this.descricao = descricao;
    }

    public String getValor(){
        return descricao;
    }
}
