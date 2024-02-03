package br.com.dbc.vemser.notifica.entity;

import br.com.dbc.vemser.notifica.entity.enums.Categoria;
import br.com.dbc.vemser.notifica.entity.enums.StatusDenuncia;
import br.com.dbc.vemser.notifica.entity.enums.TipoDenuncia;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "DENUNCIA")
//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Denuncia {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DENUNCIA_SEQ")
    @SequenceGenerator(name = "DENUNCIA_SEQ", sequenceName = "SEQ_DENUNCIA", allocationSize = 1)
    @Column(name = "id_denuncia")
    private Integer idDenuncia;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "titulo")
    private String titulo;

    @Column(name = "data_hora")
    private LocalDateTime dataHora;

    @Column(name = "status_denuncia")
    @Enumerated(EnumType.ORDINAL)
    private StatusDenuncia statusDenuncia;

    @Column(name = "categoria")
    @Enumerated(EnumType.ORDINAL)
    private Categoria categoria;

    @Column(name = "tipo_denuncia")
    @Enumerated(EnumType.ORDINAL)
    private TipoDenuncia tipoDenuncia;

    @ManyToOne
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    private Usuario usuario;

    @Formula("id_usuario")
    private Integer idUsuario;

    @JsonIgnore
    @OneToMany(mappedBy = "denuncia", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comentario> comentarios;

    @OneToOne(mappedBy = "denuncia", cascade = CascadeType.ALL, orphanRemoval = true)
    private Localizacao localizacao;

    @JsonIgnore
    @OneToMany(mappedBy = "denuncia", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Curtida> curtidas;
}
