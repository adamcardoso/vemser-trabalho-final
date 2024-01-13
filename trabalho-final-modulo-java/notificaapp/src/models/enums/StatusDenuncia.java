package models.enums;

public enum StatusDenuncia {
    ABERTO("1"),
    EM_ANALISE("2"),
    EM_ANDAMENTO("3"),
    RESOLVIDO("4"),
    FECHADO("5");

    private final String descricao;
    StatusDenuncia(String descricao){
        this.descricao = descricao;
    }
    public String getValor(){
        return descricao;
    }

    public static StatusDenuncia getEnum(String n){
        for(StatusDenuncia situacao: values())
            if(situacao.descricao.equals(n))
                return situacao;
        return null;
    }
}
