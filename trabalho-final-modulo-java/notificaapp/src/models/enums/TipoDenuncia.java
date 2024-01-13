package models.enums;

public enum TipoDenuncia {
    PUBLICA("1"),
    ANONIMA("2");
    private final String descricao;
    TipoDenuncia(String descricao){
        this.descricao = descricao;
    }
    public String getValor(){
        return descricao;
    }

    public static TipoDenuncia getEnum(String n){
        for(TipoDenuncia tipoDenuncia: values())
            if(tipoDenuncia.descricao.equals(n))
                return tipoDenuncia;
        return null;
    }
}
