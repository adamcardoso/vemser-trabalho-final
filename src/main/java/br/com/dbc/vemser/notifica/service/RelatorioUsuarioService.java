package br.com.dbc.vemser.notifica.service;

import br.com.dbc.vemser.notifica.dto.relatorio.RelatorioDto;
import br.com.dbc.vemser.notifica.dto.usuario.UsuarioDTO;
import br.com.dbc.vemser.notifica.repository.ICurtidaRepository;
import br.com.dbc.vemser.notifica.repository.IRelatorioUsuarioRepository;
import br.com.dbc.vemser.notifica.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RelatorioUsuarioService {
    private final IRelatorioUsuarioRepository relatorioUsuarioRepository;
    private final ICurtidaRepository curtidaRepository;
    private final UsuarioRepository usuarioRepository;

    public Page<UsuarioDTO> usuariosPorEstado(String estado, Pageable pageable){
        return relatorioUsuarioRepository.usuariosPorEstado(estado, pageable);
    }

    public Page<RelatorioDto> obterCurtidasUsuarios(Pageable pageable){
        return usuarioRepository.findAll(pageable).map(usuario -> {
            Integer curtidasDenuncias = curtidaRepository.curtidasDenuncia(usuario.getIdUsuario());
            Integer curtidasComentarios = curtidaRepository.curtidasComentario(usuario.getIdUsuario());

            return new RelatorioDto(usuario.getIdUsuario(), usuario.getNomeUsuario(), curtidasDenuncias, curtidasComentarios);
        });
    }
}
