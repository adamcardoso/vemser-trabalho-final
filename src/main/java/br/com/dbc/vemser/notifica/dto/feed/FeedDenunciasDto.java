package br.com.dbc.vemser.notifica.dto.feed;

import br.com.dbc.vemser.notifica.dto.usuario.UsuarioDTO;
import br.com.dbc.vemser.notifica.entity.Comentario;
import br.com.dbc.vemser.notifica.entity.Localizacao;
import br.com.dbc.vemser.notifica.entity.Usuario;
import br.com.dbc.vemser.notifica.entity.enums.Categoria;
import br.com.dbc.vemser.notifica.entity.enums.StatusDenuncia;
import br.com.dbc.vemser.notifica.entity.enums.TipoDenuncia;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class FeedDenunciasDto {
    private Integer idDenuncia;

    private String descricao;

    private String titulo;

    private LocalDateTime dataHora;

    private StatusDenuncia statusDenuncia;

    private Categoria categoria;

    private TipoDenuncia tipoDenuncia;

    private Integer curtidas;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Usuario usuario;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer idUsuario;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<Comentario> comentarios;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Localizacao localizacao;
    public FeedDenunciasDto(Integer idDenuncia, String descricao, String titulo, LocalDateTime dataHora,
                            StatusDenuncia statusDenuncia, Categoria categoria, TipoDenuncia tipoDenuncia,
                            Integer curtidas, Usuario usuario, Integer idUsuario, Localizacao localizacao){
        this.idDenuncia = idDenuncia;
        this.descricao = descricao;
        this.titulo = titulo;
        this.dataHora = dataHora;
        this.statusDenuncia = statusDenuncia;
        this.categoria = categoria;
        this.tipoDenuncia = tipoDenuncia;
        this.curtidas = curtidas;
        this.usuario = tipoDenuncia.getIdTipoDenuncia() == 0 ? usuario: null;
        this.idUsuario = tipoDenuncia.getIdTipoDenuncia() == 0 ? idUsuario : null;
        this.localizacao = localizacao;
    }

    public FeedDenunciasDto(Integer idDenuncia, String descricao, String titulo, LocalDateTime dataHora,
                            StatusDenuncia statusDenuncia, Categoria categoria, TipoDenuncia tipoDenuncia,
                            Integer curtidas, Usuario usuario, List<Comentario> comentarios, Localizacao localizacao){
        this.idDenuncia = idDenuncia;
        this.descricao = descricao;
        this.titulo = titulo;
        this.dataHora = dataHora;
        this.statusDenuncia = statusDenuncia;
        this.categoria = categoria;
        this.tipoDenuncia = tipoDenuncia;
        this.curtidas = curtidas;
        this.usuario = tipoDenuncia.getIdTipoDenuncia() == 0 ? usuario: null;
        this.comentarios = comentarios;
        this.localizacao = localizacao;
    }
}
