package br.com.dbc.vemser.notifica.dto.usuario.admin_dto;

import br.com.dbc.vemser.notifica.dto.localizacao.LocalizacaoDTO;
import br.com.dbc.vemser.notifica.entity.Curtida;
import br.com.dbc.vemser.notifica.entity.enums.Categoria;
import br.com.dbc.vemser.notifica.entity.enums.StatusDenuncia;
import br.com.dbc.vemser.notifica.entity.enums.TipoDenuncia;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class DenunciaListDTO {
    private Integer idDenuncia;
    private String descricao;
    private String titulo;
    private StatusDenuncia statusDenuncia;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<Curtida> curtidas;
    private Categoria categoria;
    private TipoDenuncia tipoDenuncia;
    private Integer idUsuario;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private LocalizacaoDTO localizacao;

}
