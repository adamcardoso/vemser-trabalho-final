package br.com.dbc.vemser.notifica.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Localizacao {
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="localizacao_sequence")
    @SequenceGenerator(name="localizacao_sequence", sequenceName="SEQ_LOCALIZACAO", allocationSize = 1)
    @Column(name = "id_localizacao")
    private Integer idLocalizacao;

    @Column(name = "latitude ")
    private String latitude;

    @Column(name = "longitude ")
    private String longitude;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "id_denuncia", referencedColumnName = "id_denuncia", insertable = false, updatable = false)
    private Denuncia denuncia;

    @Column(name = "id_denuncia ")
    private Integer idDenuncia;

    @JsonIgnore
    @OneToMany(mappedBy = "localizacao", cascade = CascadeType.ALL)
    private List<Avisos> avisos = new ArrayList<>();


}
