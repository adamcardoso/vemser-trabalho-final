package br.com.dbc.vemser.notifica.service;

import br.com.dbc.vemser.notifica.dto.denuncia.DenunciaCreateDTO;
import br.com.dbc.vemser.notifica.dto.denuncia.DenunciaDTO;
import br.com.dbc.vemser.notifica.dto.usuario.UsuarioCreateDTO;
import br.com.dbc.vemser.notifica.dto.usuario.UsuarioUpdateDTO;
import br.com.dbc.vemser.notifica.dto.usuario.UsuarioDTO;
import br.com.dbc.vemser.notifica.entity.Denuncia;
import br.com.dbc.vemser.notifica.entity.Usuario;
import br.com.dbc.vemser.notifica.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.notifica.repository.AdminRepository;
import br.com.dbc.vemser.notifica.repository.DenunciaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UsuarioAdminService {
    private final AdminRepository adminRepository;
    private final DenunciaRepository denunciaRepository;
    private final ObjectMapper objectMapper;

    public List<UsuarioDTO> list(){
        return adminRepository.findAll().stream()
                .map(usuario -> retornarDTO(usuario))
                .collect(Collectors.toList());
    }

    public List<UsuarioDTO> listUsuariosAdmin(){
        return adminRepository.usuariosAdmin().stream()
                .map(usuario -> retornarDTO(usuario))
                .collect(Collectors.toList());
    }

    public UsuarioDTO obterUsuarioById(Integer idUsuario) throws Exception {
        return retornarDTO(getUsuario(idUsuario));
    }

    public UsuarioDTO criarUsuario(UsuarioCreateDTO novoUsuario) {
        Usuario usuarioCriado = converterDTO(novoUsuario);
        return retornarDTO(adminRepository.save(usuarioCriado));
    }
    //
    public UsuarioDTO atualizarUsuario(Integer idUsuario, UsuarioUpdateDTO novoUsuario) throws Exception {
        Usuario usuarioRecuperado = getUsuario(idUsuario);
        usuarioRecuperado.setEmailUsuario(novoUsuario.getEmailUsuario());
        usuarioRecuperado.setEtniaUsuario(novoUsuario.getEtniaUsuario());
        usuarioRecuperado.setGeneroUsuario(novoUsuario.getGeneroUsuario());
        usuarioRecuperado.setNomeUsuario(novoUsuario.getNomeUsuario());
        usuarioRecuperado.setSenhaUsuario(novoUsuario.getSenhaUsuario());
        usuarioRecuperado.setTipoUsuario(novoUsuario.getTipoUsuario());
        usuarioRecuperado.setClasseSocial(novoUsuario.getClasseSocial());
        usuarioRecuperado.setDataNascimento(novoUsuario.getDataNascimento());
        usuarioRecuperado.setNumeroCelular(novoUsuario.getNumeroCelular());

        return retornarDTO(adminRepository.save(usuarioRecuperado));
    }

    public void removerUsuario(Integer idUsuario) throws Exception {
        Usuario usuarioDeletado = getUsuario(idUsuario);
        adminRepository.delete(usuarioDeletado);
    }

    public List<DenunciaDTO> listarTodasDenuncias(){
        return denunciaRepository.findAll().stream()
                .map(denuncia -> retornarDTODenuncia(denuncia))
                .collect(Collectors.toList());
    }

    public DenunciaDTO denunciaById(Integer id) throws RegraDeNegocioException {
        return retornarDTODenuncia(getUsuarioDenuncia(id));
    }

    public void deletarDenuncia(Integer id) throws RegraDeNegocioException {
        Denuncia denunciaDeletada = getUsuarioDenuncia(id);
        denunciaRepository.delete(denunciaDeletada);
    }

    private Usuario getUsuario(Integer id) throws RegraDeNegocioException {
        return adminRepository.findById(id)
                .orElseThrow(() -> new RegraDeNegocioException("Usuario não encontrado!"));
    }

    private Usuario converterDTO(UsuarioCreateDTO dto) {
        return objectMapper.convertValue(dto, Usuario.class);
    }

    private UsuarioDTO retornarDTO(Usuario entity) {
        return objectMapper.convertValue(entity, UsuarioDTO.class);
    }

    private Denuncia getUsuarioDenuncia(Integer id) throws RegraDeNegocioException {
        return denunciaRepository.findById(id)
                .orElseThrow(() -> new RegraDeNegocioException("Denuncia não encontrado!"));
    }

    private Denuncia converterDTODenuncia(DenunciaCreateDTO dto) {
        return objectMapper.convertValue(dto, Denuncia.class);
    }

    private DenunciaDTO retornarDTODenuncia(Denuncia entity) {
        return objectMapper.convertValue(entity, DenunciaDTO.class);
    }

}