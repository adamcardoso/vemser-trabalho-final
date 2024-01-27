package br.com.dbc.vemser.notifica.dto.usuario;

import br.com.dbc.vemser.notifica.entity.enums.ClasseSocial;
import br.com.dbc.vemser.notifica.entity.enums.Etnia;
import br.com.dbc.vemser.notifica.entity.enums.Genero;
import br.com.dbc.vemser.notifica.entity.enums.TipoUsuario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDate;

@AllArgsConstructor
@Data
public class UsuarioDTO {
    private int idUsuario;
    private String nomeUsuario;
    private String emailUsuario;
    private String numeroCelular;

    @ToString.Exclude
    private String senhaUsuario;
    private Etnia etniaUsuario;
    private LocalDate dataNascimento;
    private ClasseSocial classeSocial;
    private Genero generoUsuario;
    private TipoUsuario tipoUsuario;
}
