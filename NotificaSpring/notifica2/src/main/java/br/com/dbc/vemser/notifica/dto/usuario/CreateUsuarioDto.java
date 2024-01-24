package br.com.dbc.vemser.notifica.dto.usuario;

import br.com.dbc.vemser.notifica.entity.enums.ClasseSocial;
import br.com.dbc.vemser.notifica.entity.enums.Etnia;
import br.com.dbc.vemser.notifica.entity.enums.Genero;
import br.com.dbc.vemser.notifica.entity.enums.TipoUsuario;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@AllArgsConstructor
@Data
public class CreateUsuarioDto {
    @Schema(description = "ID do Usuário", example = "1")
    private int idUsuario;

    @NotNull
    @NotEmpty
    @Schema(description = "Nome do Usuário", required = true, example = "John Doe")
    private String nomeUsuario;

    @NotBlank
    @Schema(description = "Número de Celular do Usuário", required = true, example = "123456789")
    private String numeroCelular;

    @NotNull
    @NotEmpty
    @Schema(description = "Senha do Usuário", required = true, example = "Senha123@")
    private String senhaUsuario;

    @NotNull
    @Schema(description = "Etnia do Usuário", required = true, example = "BRANCO", allowableValues = {"BRANCO", "NEGRO", "INDIGENA", "PARDO", "AMARELO"})
    private Etnia etniaUsuario;

    @NotNull
    @Schema(description = "Data de Nascimento do Usuário", required = true, example = "1990-01-01")
    private LocalDate dataNascimento;

    @NotNull
    @Schema(description = "Classe Social do Usuário", required = true, example = "A", allowableValues = {"A","B","C","D","E"})
    private ClasseSocial classeSocial;

    @NotNull
    @Schema(description = "Gênero do Usuário", required = true, example = "MASCULINO", allowableValues = {"MASCULINO", "FEMININO", "OUTRO"})
    private Genero generoUsuario;

    @NotNull
    @Schema(description = "Tipo de Usuário", required = true, example = "COMUM", allowableValues = {"COMUM", "ADMIN"})
    private TipoUsuario tipoUsuario;
}
