package br.com.dbc.vemser.notifica.dto.usuario.admin_dto;

import br.com.dbc.vemser.notifica.entity.enums.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioListDTO {
    private Integer idUsuario;

    private String nomeUsuario;

    private String emailUsuario;

    private String numeroCelular;

    private TipoUsuario tipoUsuario;

    private UsuarioAtivo usuarioAtivo;
}
