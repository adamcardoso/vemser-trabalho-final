package br.com.dbc.vemser.notifica.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

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

    @JsonIgnore
    @OneToMany(mappedBy = "comentario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Curtida> curtidas;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_denuncia", referencedColumnName = "id_denuncia")
    private Denuncia denuncia;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    private Usuario usuario;
}