package br.com.dbc.vemser.notifica.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Comentario {
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="comentario_sequence")
    @SequenceGenerator(name="comentario_sequence", sequenceName="seq_comentario", allocationSize = 1)
    @Column(name = "id_comentario")
    private Integer idComentario;

    @Column
    private String comentario;

    @Column
    private Integer curtida;


    @ManyToOne
    @JoinColumn(name = "id_denuncia", referencedColumnName = "id_denuncia")
    private Denuncia denuncia;

    private Integer idUsuario;

}