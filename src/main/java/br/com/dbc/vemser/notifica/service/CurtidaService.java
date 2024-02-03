package br.com.dbc.vemser.notifica.service;

import br.com.dbc.vemser.notifica.dto.curtida.CurtidaDto;
import br.com.dbc.vemser.notifica.entity.Comentario;
import br.com.dbc.vemser.notifica.entity.Curtida;
import br.com.dbc.vemser.notifica.entity.Denuncia;
import br.com.dbc.vemser.notifica.entity.Usuario;
import br.com.dbc.vemser.notifica.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.notifica.repository.DenunciaRepository;
import br.com.dbc.vemser.notifica.repository.IComentarioRepository;
import br.com.dbc.vemser.notifica.repository.ICurtidaRepository;
import br.com.dbc.vemser.notifica.repository.UsuarioRepository;
import br.com.dbc.vemser.notifica.service.interfaces.ICurtidaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CurtidaService implements ICurtidaService {
    private final ICurtidaRepository curtidaRepository;
    private final UsuarioRepository usuarioRepository;
    private final IComentarioRepository comentarioRepository;
    private final DenunciaRepository denunciaRepository;

    @Override
    public void apoiarComentario(CurtidaDto curtidaDto) throws Exception {
        Optional<Curtida> cuOpt = curtidaRepository.findByIdUsuarioAndIdComentario(curtidaDto.getIdUsuario(), curtidaDto.getIdComentario());

        if(cuOpt.isPresent())
            this.removerApoio(cuOpt.get().getIdCurtida());
        else
            this.adicionarApoioComentario(curtidaDto);
    }

    @Override
    public void apoiarDenuncia(CurtidaDto curtidaDto) throws Exception {
        Optional<Curtida> cuOpt = curtidaRepository.findByIdUsuarioAndDenuncia(curtidaDto.getIdUsuario(), curtidaDto.getIdDenuncia());

        if(cuOpt.isPresent())
            this.removerApoio(cuOpt.get().getIdCurtida());
        else
            this.adicionarApoioDenuncia(curtidaDto);
    }

    private void adicionarApoioComentario(CurtidaDto curtidaDto) throws Exception {
        Usuario u = usuarioRepository.findById(curtidaDto.getIdUsuario()).orElseThrow(() -> new RegraDeNegocioException("Usuário não encontrado!"));
        Comentario c = comentarioRepository.findById(curtidaDto.getIdComentario()).orElseThrow(() -> new RegraDeNegocioException("Comentário não encontrado!"));
        curtidaRepository.save(new Curtida(u, c, LocalDateTime.now()));
    }

    private void adicionarApoioDenuncia(CurtidaDto curtidaDto) throws Exception{
        Usuario u = usuarioRepository.findById(curtidaDto.getIdUsuario()).orElseThrow(() -> new RegraDeNegocioException("Usuário não encontrado!"));
        Denuncia d = denunciaRepository.findById(curtidaDto.getIdDenuncia()).orElseThrow(() -> new RegraDeNegocioException("Denúncia não encontrada!"));
        curtidaRepository.save(new Curtida(u, d, LocalDateTime.now()));
    }

    private void removerApoio(Integer id){
        curtidaRepository.deleteById(id);
    }
}
