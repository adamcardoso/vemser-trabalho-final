package br.com.dbc.vemser.notifica.dto.usuario;

import br.com.dbc.vemser.notifica.entity.Endereco;
import br.com.dbc.vemser.notifica.entity.enums.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioPerfilDTO {
    private Integer idUsuario;

    private String nomeUsuario;

    private String emailUsuario;

    private String numeroCelular;

    private Etnia etniaUsuario;

    private LocalDate dataNascimento;

    private ClasseSocial classeSocial;

    private Genero generoUsuario;

    private TipoUsuario tipoUsuario;

    private UsuarioAtivo usuarioAtivo;

    private List<Endereco> enderecos;
}
