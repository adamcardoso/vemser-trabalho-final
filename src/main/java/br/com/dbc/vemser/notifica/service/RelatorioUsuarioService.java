package br.com.dbc.vemser.notifica.service;

import br.com.dbc.vemser.notifica.dto.denuncia.DenunciaDTO;
import br.com.dbc.vemser.notifica.dto.endereco.EnderecoDTO;
import br.com.dbc.vemser.notifica.dto.relatorio.RelatorioDto;
import br.com.dbc.vemser.notifica.dto.usuario.UsuarioDTO;
import br.com.dbc.vemser.notifica.entity.Endereco;
import br.com.dbc.vemser.notifica.entity.Usuario;
import br.com.dbc.vemser.notifica.repository.ICurtidaRepository;
import br.com.dbc.vemser.notifica.repository.IRelatorioUsuarioRepository;
import br.com.dbc.vemser.notifica.repository.UsuarioRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class RelatorioUsuarioService {
    private final IRelatorioUsuarioRepository relatorioUsuarioRepository;
    private final ICurtidaRepository curtidaRepository;
    private final UsuarioRepository usuarioRepository;
    private final ObjectMapper objectMapper;

    public Page<UsuarioDTO> usuariosPorEstado(String estado, Pageable pageable) {
        return relatorioUsuarioRepository.usuariosPorEstado(estado, pageable)
                .map(this::retornarDTO);
    }

    public Page<UsuarioDTO> usuariosPorCidade(String cidade, Pageable pageable) {
        String estado2 = "%" + cidade + "%";
        return relatorioUsuarioRepository.usuariosPorCidade(estado2, pageable)
                .map(this::retornarDTO);
    }

    public UsuarioDTO retornarDTO(Usuario usuario) {
        return new UsuarioDTO(
                usuario.getIdUsuario(), usuario.getNomeUsuario(),
                usuario.getEmailUsuario(), usuario.getNumeroCelular(),
                usuario.getEtniaUsuario(), usuario.getDataNascimento(),
                usuario.getClasseSocial(), usuario.getGeneroUsuario()
        );
    }

    public Page<RelatorioDto> obterCurtidasUsuarios(Pageable pageable){
        return usuarioRepository.findAll(pageable).map(usuario -> {
            Integer curtidasDenuncias = curtidaRepository.curtidasDenuncia(usuario.getIdUsuario());
            Integer curtidasComentarios = curtidaRepository.curtidasComentario(usuario.getIdUsuario());

            return new RelatorioDto(usuario.getIdUsuario(), usuario.getNomeUsuario(), curtidasDenuncias, curtidasComentarios);
        });
    }
}
