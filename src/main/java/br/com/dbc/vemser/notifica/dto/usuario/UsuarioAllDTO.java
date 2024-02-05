package br.com.dbc.vemser.notifica.dto.usuario;

import br.com.dbc.vemser.notifica.dto.comentario.ComentarioDTO;
import br.com.dbc.vemser.notifica.dto.denuncia.DenunciaDTO;
import br.com.dbc.vemser.notifica.dto.endereco.EnderecoDTO;
import br.com.dbc.vemser.notifica.entity.Comentario;
import br.com.dbc.vemser.notifica.entity.Denuncia;
import br.com.dbc.vemser.notifica.entity.Endereco;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class UsuarioAllDTO extends UsuarioDTO{
    private List<DenunciaDTO> denuncias;
    private List<ComentarioDTO> comentarios;
    private List<EnderecoDTO> enderecos;
}
