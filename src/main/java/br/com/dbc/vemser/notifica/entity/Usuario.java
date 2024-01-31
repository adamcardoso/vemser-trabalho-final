package br.com.dbc.vemser.notifica.entity;

import br.com.dbc.vemser.notifica.entity.enums.ClasseSocial;
import br.com.dbc.vemser.notifica.entity.enums.Etnia;
import br.com.dbc.vemser.notifica.entity.enums.Genero;
import br.com.dbc.vemser.notifica.entity.enums.TipoUsuario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "USUARIO")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USUARIO_SEQ")
    @SequenceGenerator(name = "USUARIO_SEQ", sequenceName = "SEQ_USUARIO", allocationSize = 1)
    @Column(name = "id_usuario")
    private Integer idUsuario;

    @Column(name = "nome_usuario")
    private String nomeUsuario;

    @Column(name = "email_usuario")
    private String emailUsuario;

    @Column(name = "celular_usuario")
    private String numeroCelular;

    @Column(name = "senha_usuario")
    private String senhaUsuario;

    @Column(name = "etnia")
    @Enumerated(EnumType.ORDINAL)
    private Etnia etniaUsuario;

    @Column(name = "data_nascimento")
    private LocalDate dataNascimento;

    @Column(name = "classe_social")
    @Enumerated(EnumType.ORDINAL)
    private ClasseSocial classeSocial;

    @Column(name = "genero")
    @Enumerated(EnumType.ORDINAL)
    private Genero generoUsuario;

    @Column(name = "tipo_usuario")
    @Enumerated(EnumType.ORDINAL)
    private TipoUsuario tipoUsuario;
    //private boolean isAdmin;

}
