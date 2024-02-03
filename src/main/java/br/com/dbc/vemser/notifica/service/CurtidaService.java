package br.com.dbc.vemser.notifica.service;

import br.com.dbc.vemser.notifica.dto.comentario.ComentarioCreateDTO;
import br.com.dbc.vemser.notifica.dto.comentario.ComentarioDTO;
import br.com.dbc.vemser.notifica.dto.curtida.CurtidaDto;
import br.com.dbc.vemser.notifica.entity.Comentario;
import br.com.dbc.vemser.notifica.entity.Curtida;
import br.com.dbc.vemser.notifica.entity.Denuncia;
import br.com.dbc.vemser.notifica.entity.Usuario;
import br.com.dbc.vemser.notifica.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.notifica.repository.IComentarioRepository;
import br.com.dbc.vemser.notifica.repository.ICurtidaRepository;
import br.com.dbc.vemser.notifica.repository.UsuarioRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CurtidaService {
    private final ICurtidaRepository curtidaRepository;
    private final UsuarioRepository usuarioRepository;
    private final IComentarioRepository comentarioRepository;

    public void apoiar(CurtidaDto curtidaDto) throws Exception {
        Optional<Curtida> cuOpt = curtidaRepository.findByIdUsuarioAndIdComentario(curtidaDto.getIdUsuario(), curtidaDto.getIdComentario());

        if(cuOpt.isPresent())
            this.removerApoio(cuOpt.get().getIdCurtida());
        else
            this.adicionarApoio(curtidaDto);
    }

    private void adicionarApoio(CurtidaDto curtidaDto) throws Exception {
        Usuario u = usuarioRepository.findById(curtidaDto.getIdUsuario()).orElseThrow(() -> new RegraDeNegocioException("Usuário não encontrado!"));
        Comentario c = comentarioRepository.findById(curtidaDto.getIdComentario()).orElseThrow(() -> new RegraDeNegocioException("Comentário não encontrado!"));
        curtidaRepository.save(new Curtida(u, c, LocalDateTime.now()));
    }

    private void removerApoio(Integer id){
        curtidaRepository.deleteById(id);
    }
}
