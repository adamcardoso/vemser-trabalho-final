package br.com.dbc.vemser.notifica.dto.usuario;

import br.com.dbc.vemser.notifica.entity.enums.ClasseSocial;
import br.com.dbc.vemser.notifica.entity.enums.Etnia;
import br.com.dbc.vemser.notifica.entity.enums.Genero;
import br.com.dbc.vemser.notifica.entity.enums.TipoUsuario;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UsuarioDTO {
    private Integer idUsuario;
    private String nomeUsuario;
    private String emailUsuario;
    private String numeroCelular;
    private Etnia etniaUsuario;
    private LocalDate dataNascimento;
    private ClasseSocial classeSocial;
    private Genero generoUsuario;

}
