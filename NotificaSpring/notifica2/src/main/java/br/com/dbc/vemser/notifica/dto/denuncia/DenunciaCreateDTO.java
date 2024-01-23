package br.com.dbc.vemser.notifica.dto.denuncia;

import br.com.dbc.vemser.notifica.entity.Localizacao;
import br.com.dbc.vemser.notifica.entity.Usuario;
import br.com.dbc.vemser.notifica.entity.enums.Categoria;
import br.com.dbc.vemser.notifica.entity.enums.StatusDenuncia;
import br.com.dbc.vemser.notifica.entity.enums.TipoDenuncia;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DenunciaCreateDTO {
    private int idDenuncia;
    private String descricao;
    @NotNull(message = "A Denúncia precisa ter um Título!")
    private String titulo;
    private Localizacao local;
    private LocalDateTime dataHora;
    private StatusDenuncia statusDenuncia;
    private Categoria categoria;
    private int curtidas;
    private final List<String> comentarios = new ArrayList<>();
    private TipoDenuncia tipoDenuncia;
    private int idUsuario;
}
