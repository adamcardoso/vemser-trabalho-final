package br.com.dbc.vemser.notifica.dto.comentario;

import br.com.dbc.vemser.notifica.dto.denuncia.DenunciaDTO;
import br.com.dbc.vemser.notifica.dto.usuario.UsuarioDTO;
import br.com.dbc.vemser.notifica.entity.Denuncia;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ComentarioDTO {
    private Integer idComentario;
    private String comentario;
    private Integer curtida;
    private Integer idDenuncia;
    private Integer idUsuario;
    @JsonIgnore
    private DenunciaDTO denuncia;
    @JsonIgnore
    private UsuarioDTO usuario;
}
