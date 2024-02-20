package br.com.dbc.vemser.notifica.dto.usuario;

import br.com.dbc.vemser.notifica.entity.enums.ClasseSocial;
import br.com.dbc.vemser.notifica.entity.enums.Etnia;
import br.com.dbc.vemser.notifica.entity.enums.Genero;
import br.com.dbc.vemser.notifica.entity.enums.TipoUsuario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class UsuarioUpdateDTO {
    @NotNull
    @NotEmpty
    @NotBlank
    private String nomeUsuario;

    private String emailUsuario;

    @NotNull
    @NotEmpty
    @NotBlank
    private String numeroCelular;

    @NotNull
    private Etnia etniaUsuario;

    @NotNull
    private LocalDate dataNascimento;

    @NotNull
    private ClasseSocial classeSocial;

    @NotNull
    private Genero generoUsuario;

}
