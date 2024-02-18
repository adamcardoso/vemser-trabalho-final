package br.com.dbc.vemser.notifica.dto.avisos;

import br.com.dbc.vemser.notifica.dto.localizacao.LocalizacaoCreateDTO;
import br.com.dbc.vemser.notifica.dto.localizacao.LocalizacaoDTO;
import br.com.dbc.vemser.notifica.entity.Instituicao;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Setter
@Getter
public class AvisosDTO {
    private String message;
    private String data;
    private String hora;
    private LocalizacaoDTO localizacao;
}
