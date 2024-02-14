package br.com.dbc.vemser.notifica.dto.avisos;

import br.com.dbc.vemser.notifica.dto.localizacao.LocalizacaoCreateDTO;
import br.com.dbc.vemser.notifica.dto.localizacao.LocalizacaoDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Setter
@Getter
public class AvisosCreateDTO {
    @Schema(description = "Mensagem", required = true, example = "Ocorrerá Falta de Água em sua Localidade")
    private String message;
    private Date data;
    @Schema(description = "Hora", required = true, example = "12h")
    private String hora;
    private LocalizacaoCreateDTO localizacao;
}
