package br.com.dbc.vemser.notifica.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Localizacao {
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="localizacao_sequence")
    @SequenceGenerator(name="localizacao_sequence", sequenceName="seq_localizacao", allocationSize = 1)
    @Column(name = "id_localizacao")
    private Integer idLocalizacao;

    @Column
    private String latitude;

    @Column
    private String longitude;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "id_denuncia", referencedColumnName = "id_denuncia", unique = true)
    private Denuncia denuncia;

    public Localizacao(String latitude, String longitude, Denuncia denuncia){
        this.latitude = latitude;
        this.longitude = longitude;
        this.denuncia = denuncia;
    }
}
