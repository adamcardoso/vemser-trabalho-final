package br.com.dbc.vemser.notifica.service;

import br.com.dbc.vemser.notifica.dto.relatorio.RelatorioDto;
import br.com.dbc.vemser.notifica.entity.Curtida;
import br.com.dbc.vemser.notifica.entity.Usuario;
import br.com.dbc.vemser.notifica.repository.ICurtidaRepository;
import br.com.dbc.vemser.notifica.repository.IRelatorioUsuarioRepository;
import br.com.dbc.vemser.notifica.repository.UsuarioRepository;
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

    public Page<Usuario> usuariosPorEstado(String estado, Pageable pageable){
        return relatorioUsuarioRepository.usuariosPorEstado(estado, pageable);
    }

    public Page<RelatorioDto> teste(Pageable pageable){
        return usuarioRepository.findAll(pageable).map(usuario -> {
            Integer curtidasDenuncias = curtidaRepository.curtidasDenuncia(usuario.getIdUsuario());
            Integer curtidasComentarios = curtidaRepository.curtidasComentario(usuario.getIdUsuario());

            return new RelatorioDto(usuario.getIdUsuario(), usuario.getNomeUsuario(), curtidasDenuncias, curtidasComentarios);
        });
//        List<Usuario> usuarios = usuarioRepository.findAll().stream().map();
//
//        for (Usuario usuario: usuarios){
//            Integer curtidas_denuncias = curtidaRepository.curtidasDenuncia(usuario.getIdUsuario());
//            Integer curtidas_comentario = curtidaRepository.curtidasComentario(usuario.getIdUsuario());
//        }
    }
}
//Pessoa p = pessoaRepository.getById(id);
//PessoaDto pessoa = objectMapper.convertValue(p, PessoaDto.class);
//        pessoa.setIdPet(p.getPet() != null ? p.getPet().getIdPet() : null);
//
//List<ContatoDto> contatos = contatoRepository.findByIdPessoa(pessoa.getIdPessoa())
//        .stream()
//        .map(c -> objectMapper.convertValue(c, ContatoDto.class))
//        .collect(Collectors.toList());
//
//List<EnderecoDto> enderecos = enderecoRepository.listarEnderecoPorIdPessoa(pessoa.getIdPessoa())
//        .stream()
//        .map(e -> objectMapper.convertValue(e, EnderecoDto.class))
//        .collect(Collectors.toList());
//
//PetEntity pet = petRepository.getById(pessoa.getIdPet());
//
//        return new ArrayList<>(List.of(new PessoaRelatorioDto(pessoa, contatos, enderecos, pet)));