package br.com.dbc.vemser.notifica.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "AVISOS")
public class Avisos {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "aviso_sequence")
    @SequenceGenerator(name = "aviso_sequence", sequenceName = "SEQ_AVISOS", allocationSize = 1)
    @Column(name = "id_aviso")
    private Integer idAviso;

    @Column(name = "message")
    private String message;

    @Column(name = "data")
    private Date data;

    @Column(name = "hora")
    private String hora;

    @ManyToOne
    @JoinColumn(name = "id_localizacao", referencedColumnName = "id_localizacao")
    private Localizacao localizacao;

    @ManyToOne
    @JoinColumn(name = "id_instituicao", referencedColumnName = "id_instituicao")
    private Instituicao instituicao;

    @Column(name = "id_instituicao", insertable = false, updatable = false)
    private Integer idInstituicao;
}
