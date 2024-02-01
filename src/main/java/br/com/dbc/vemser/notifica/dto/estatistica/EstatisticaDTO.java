package br.com.dbc.vemser.notifica.dto.estatistica;

import br.com.dbc.vemser.notifica.entity.enums.ClasseSocial;
import br.com.dbc.vemser.notifica.entity.enums.Etnia;
import br.com.dbc.vemser.notifica.entity.enums.Genero;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class EstatisticaDTO {

    @Schema(description = "Etnia do Usuário", example = "BRANCO")
    private Etnia etniaUsuario;

    @Schema(description = "Classe Social do Usuário", example = "MEDIA")
    private ClasseSocial classeSocial;

    @Schema(description = "Gênero do Usuário", example = "MASCULINO")
    private Genero generoUsuario;

    @Schema(description = "Total da Estatística", example = "100")
    private Integer total;

    @Schema(description = "Percentual da Estatística", example = "25.5%")
    private double percentual;

    @Schema(description = "Data de Cadastro", example = "2024-02-01")
    private LocalDate ultimaDataCadastro;

}
