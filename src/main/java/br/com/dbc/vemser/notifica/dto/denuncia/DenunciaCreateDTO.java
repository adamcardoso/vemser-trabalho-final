package br.com.dbc.vemser.notifica.dto.denuncia;

import br.com.dbc.vemser.notifica.entity.Localizacao;
import br.com.dbc.vemser.notifica.entity.Usuario;
import br.com.dbc.vemser.notifica.entity.enums.Categoria;
import br.com.dbc.vemser.notifica.entity.enums.StatusDenuncia;
import br.com.dbc.vemser.notifica.entity.enums.TipoDenuncia;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DenunciaCreateDTO {
    @Schema(description = "ID da Denúncia", example = "1")
    private Integer idDenuncia;

    @Schema(description = "Descrição da Denúncia", example = "Isso é uma denúncia")
    private String descricao;

    @NotBlank(message = "A Denúncia precisa ter um Título!")
    @Schema(description = "Título da Denúncia", required = true, example = "Título da Denúncia")
    private String titulo;

    @Schema(description = "Data e Hora da Denúncia", example = "2024-01-24T10:30:00")
    private LocalDateTime dataHora;

    @Schema(description = "Status da Denúncia", example = "ABERTO", allowableValues = {"ABERTO", "EM_ANALISE", "EM_ANDAMENTO", "RESOLVIDO", "FECHADO"})
    private StatusDenuncia statusDenuncia;

    @Schema(description = "Categoria da Denúncia", example = "AGUA_POTAVEL", allowableValues = {"AGUA_POTAVEL", "SANEAMENTO_BASICO", "GESTAO_RESIDUOS", "EDUCACAO_HIGIENE"})
    private Categoria categoria;

    @Schema(description = "Tipo da Denúncia", example = "ANONIMA", allowableValues = {"PUBLICA", "ANONIMA"})
    private TipoDenuncia tipoDenuncia;

    @Schema(description = "ID do Usuário que fez a Denúncia", example = "1")
    private Integer idUsuario;
}
