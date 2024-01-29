package br.com.dbc.vemser.notifica.dto.usuario;

import br.com.dbc.vemser.notifica.entity.enums.ClasseSocial;
import br.com.dbc.vemser.notifica.entity.enums.Etnia;
import br.com.dbc.vemser.notifica.entity.enums.Genero;
import br.com.dbc.vemser.notifica.entity.enums.TipoUsuario;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.*;
import java.time.LocalDate;

@AllArgsConstructor
@Data
public class UsuarioCreateDTO {
    @Schema(description = "ID do Usuário", example = "1")
    private Integer idUsuario;

    @NotNull
    @NotEmpty
    @Schema(description = "Nome do Usuário", required = true, example = "Renata Oliveira")
    private String nomeUsuario;

    @NotNull
    @NotEmpty
    @Schema(description = "E-mail do Usuário", required = true, example = "nome.sobrenome@gmail.com")
    private String emailUsuario;

    @NotBlank
    @Schema(description = "Número de Celular do Usuário", required = true, example = "998427710")
    private String numeroCelular;

    @NotNull
    @NotEmpty
    @Size(min = 6, message = "A senha deve ter pelo menos 6 caracteres")
    @Pattern(regexp = "^(?=.*[!@#$%^&*(),.?\":{}|<>]).*$", message = "A senha deve conter pelo menos 1 caractere especial")
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