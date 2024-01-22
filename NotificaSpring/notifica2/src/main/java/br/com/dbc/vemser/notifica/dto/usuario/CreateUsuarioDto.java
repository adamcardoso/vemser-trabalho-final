package br.com.dbc.vemser.notifica.dto.usuario;

import br.com.dbc.vemser.notifica.entity.enums.ClasseSocial;
import br.com.dbc.vemser.notifica.entity.enums.Etnia;
import br.com.dbc.vemser.notifica.entity.enums.Genero;
import br.com.dbc.vemser.notifica.entity.enums.TipoUsuario;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@AllArgsConstructor
@Data
public class CreateUsuarioDto {
    private int idUsuario;

    @NotNull
    @NotEmpty
    @NotBlank
    private String nomeUsuario;

    @NotNull
    @NotEmpty
    @NotBlank
    private String numeroCelular;

    @NotNull
    @NotEmpty
    @NotBlank
    private String senhaUsuario;

    @NotNull
    private Etnia etniaUsuario;

    @NotNull
    private LocalDate dataNascimento;

    @NotNull
    private ClasseSocial classeSocial;

    @NotNull
    private Genero generoUsuario;

    @NotNull
    private TipoUsuario tipoUsuario;
}
