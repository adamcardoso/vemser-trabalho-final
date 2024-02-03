package br.com.dbc.vemser.notifica.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Curtida {
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="curtida_sequence")
    @SequenceGenerator(name="curtida_sequence", sequenceName="seq_curtida", allocationSize = 1)
    @Column(name = "id_curtida")
    private Integer idCurtida;

    @Column
    private LocalDateTime dataHora;


    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_denuncia", referencedColumnName = "id_denuncia")
    private Denuncia denuncia;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    private Usuario usuario;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_comentario", referencedColumnName = "id_comentario")
    private Comentario comentario;

    public Curtida(Usuario usuario, Comentario comentario, LocalDateTime dataHora){
        this.usuario = usuario;
        this.comentario = comentario;
        this.dataHora = dataHora;
    }
}
