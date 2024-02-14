package br.com.dbc.vemser.notifica.dto.localizacao;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LocalizacaoDTO {
    private String latitude;
    private String longitude;
}
