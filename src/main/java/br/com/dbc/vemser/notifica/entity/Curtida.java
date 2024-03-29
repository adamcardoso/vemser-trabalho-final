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
@Entity(name = "CURTIDA")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Curtida {
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="curtida_sequence")
    @SequenceGenerator(name="curtida_sequence", sequenceName="seq_curtida", allocationSize = 1)
    @Column(name = "id_curtida")
    private Integer idCurtida;

    @Column(name = "id_denuncia", insertable = false, updatable = false)
    private Integer idDenuncia;
    @Column(name = "id_usuario", insertable = false, updatable = false)
    private Integer idUsuario;
    @Column(name = "id_comentario", insertable = false, updatable = false)
    private Integer idComentario;

    @Column(name = "data_hora")
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

    public Curtida(Usuario usuario, Denuncia denuncia, LocalDateTime dataHora){
        this.usuario = usuario;
        this.denuncia = denuncia;
        this.dataHora = dataHora;
    }
}
