package models.enums;

public enum Categoria {
    AGUA_POTAVEL("1"),
    SANEAMENTO_BASICO("2"),
    GESTAO_RESIDUOS("3"),
    EDUCACAO_HIGIENE("4");

    private final String descricao;
    Categoria(String descricao){
        this.descricao = descricao;
    }
    public String getValor(){
        return descricao;
    }

    public static Categoria getEnum(String n){
        for(Categoria categoria: values())
            if(categoria.descricao.equals(n))
                return categoria;
        return null;
    }
}
