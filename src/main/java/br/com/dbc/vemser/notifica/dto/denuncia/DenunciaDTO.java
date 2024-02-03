package br.com.dbc.vemser.notifica.dto.denuncia;

import br.com.dbc.vemser.notifica.dto.comentario.ComentarioDTO;
import br.com.dbc.vemser.notifica.dto.usuario.UsuarioDTO;
import br.com.dbc.vemser.notifica.entity.Localizacao;
import br.com.dbc.vemser.notifica.entity.enums.Categoria;
import br.com.dbc.vemser.notifica.entity.enums.StatusDenuncia;
import br.com.dbc.vemser.notifica.entity.enums.TipoDenuncia;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class DenunciaDTO {
        private Integer idDenuncia;
        private String descricao;
        private String titulo;
        private LocalDateTime dataHora;
        private StatusDenuncia statusDenuncia;
        private Integer curtidas;
        private Categoria categoria;
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private List<ComentarioDTO> comentarios;
        private TipoDenuncia tipoDenuncia;

        private UsuarioDTO usuario;

        private Localizacao localizacao;

        public DenunciaDTO(Integer idDenuncia, String descricao, String titulo, LocalDateTime dataHora, StatusDenuncia statusDenuncia, Integer curtidas, Categoria categoria, List<ComentarioDTO> comentarios, TipoDenuncia tipoDenuncia, UsuarioDTO usuario, Localizacao localizacao) {
                this.idDenuncia = idDenuncia;
                this.descricao = descricao;
                this.titulo = titulo;
                this.dataHora = dataHora;
                this.statusDenuncia = statusDenuncia;
                this.curtidas = curtidas;
                this.categoria = categoria;
                this.comentarios = comentarios;
                this.tipoDenuncia = tipoDenuncia;
                this.usuario = usuario;

                if (curtidas == null){
                        this.curtidas = 0;
                }

                this.localizacao = localizacao;
        }

}
