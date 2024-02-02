package br.com.dbc.vemser.notifica.dto.denuncia;

import br.com.dbc.vemser.notifica.dto.comentario.ComentarioDTO;
import br.com.dbc.vemser.notifica.dto.usuario.UsuarioDTO;
import br.com.dbc.vemser.notifica.entity.Comentario;
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
public class DenunciaDTO {
        private Integer idDenuncia;
        private String descricao;
        private String titulo;
        private LocalDateTime dataHora;
        private StatusDenuncia statusDenuncia;
        private Integer curtidas;
        private Categoria categoria;
        private List<ComentarioDTO> comentarios;
        private TipoDenuncia tipoDenuncia;
        private UsuarioDTO usuario;

}
