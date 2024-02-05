package br.com.dbc.vemser.notifica.dto.estatistica;

import br.com.dbc.vemser.notifica.entity.enums.ClasseSocial;
import br.com.dbc.vemser.notifica.entity.enums.Etnia;
import br.com.dbc.vemser.notifica.entity.enums.Genero;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class EstatisticaDTO {

    @Schema(description = "Estatísticas por etnia do usuário", example = "{\"PARDO\": 40.0, \"PRETO\": 20.0, \"AMARELO\": 40.0}")
    @JsonFormat(shape = JsonFormat.Shape.OBJECT)
    private Map<Etnia, Double> etniaUsuario;

    @Schema(description = "Estatísticas por classe social do usuário", example = "{\"D\": 60.0, \"E\": 40.0}")
    @JsonFormat(shape = JsonFormat.Shape.OBJECT)
    private Map<ClasseSocial, Double> classeSocial;

    @Schema(description = "Estatísticas por gênero do usuário", example = "{\"MASCULINO\": 40.0, \"OUTRO\": 20.0, \"FEMININO\": 40.0}")
    @JsonFormat(shape = JsonFormat.Shape.OBJECT)
    private Map<Genero, Double> generoUsuario;

    @Schema(description = "Data de Cadastro", example = "2024-02-01")
    private LocalDate ultimaDataCadastro;
}
