package br.com.dbc.vemser.notifica.service;

import br.com.dbc.vemser.notifica.dto.denuncia.DenunciaDTO;
import br.com.dbc.vemser.notifica.dto.usuario.CreateUsuarioDto;
import br.com.dbc.vemser.notifica.dto.usuario.UpdateUsuarioDto;
import br.com.dbc.vemser.notifica.dto.usuario.UsuarioDto;
import br.com.dbc.vemser.notifica.entity.Denuncia;
import br.com.dbc.vemser.notifica.entity.Usuario;
import br.com.dbc.vemser.notifica.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.notifica.repository.AdminRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UsuarioAdminService {
    private final AdminRepository adminRepository;
    private final ObjectMapper objectMapper;


    public UsuarioDto obterUsuarioById(Integer idUsuario) throws Exception {
        return objectMapper.convertValue(getUsuario(idUsuario), UsuarioDto.class);
    }

    public List<UsuarioDto> listarUsuarios() throws Exception {
        List<Usuario> usuarios = adminRepository.listarUsuarios();
        return objectMapper.convertValue(usuarios, objectMapper.getTypeFactory().constructCollectionType(List.class, UsuarioDto.class));
    }

    public List<UsuarioDto> listarAdmins() throws Exception {
        List<Usuario> usuarios = adminRepository.listarAdmins();
        return objectMapper.convertValue(usuarios, objectMapper.getTypeFactory().constructCollectionType(List.class, UsuarioDto.class));
    }

    public UsuarioDto criarUsuarioAdmin(CreateUsuarioDto novoUsuario) throws Exception {
        Usuario novoUsuarioEntity = objectMapper.convertValue(novoUsuario, Usuario.class);
        Usuario usuarioCriado = adminRepository.criarUsuarioAdmin(novoUsuarioEntity);

        return objectMapper.convertValue(usuarioCriado, UsuarioDto.class);
    }

    public UsuarioDto atualizarAdmin(Integer idUsuario, UpdateUsuarioDto novoUsuario) throws Exception {
        Usuario novoUsuarioEntity = objectMapper.convertValue(novoUsuario, Usuario.class);
        Usuario usuarioAtualizado = adminRepository.atualizarAdmin(idUsuario, novoUsuarioEntity);

        return objectMapper.convertValue(usuarioAtualizado, UsuarioDto.class);
    }

    public String removerUsuario(Integer idUsuario) throws Exception {
        if (adminRepository.removerUsuario(idUsuario)) {
            return "Usuário excluído com sucesso!";
        }
        throw new RegraDeNegocioException("Usuário não encontrado com o ID fornecido.");
    }


    private Usuario getUsuario(Integer id) throws Exception {
        Usuario usuarioRecuperado = adminRepository.listarUsuarios().stream()
                .filter(usuario -> usuario.getIdUsuario() == id)
                .findFirst()
                .orElseThrow(() -> new RegraDeNegocioException("Pessoa não encontrada!"));
        return usuarioRecuperado;
    }

    public List<DenunciaDTO> listarTodasDenuncias() throws Exception {
        return adminRepository.listarTodasDenuncias();
    }

    public String deletarDenuncia(Integer idDenuncia) throws Exception{
        if (adminRepository.deletarDenuncia(idDenuncia)) {
            return "Denúncia excluído com sucesso!";
        }
        throw new RegraDeNegocioException("Denúncia não encontrada com o ID fornecido.");
    }

    public DenunciaDTO obterDenunciaById(Integer idDenuncia) throws Exception {
        Denuncia denuncia = adminRepository.obterDenunciaById(idDenuncia);
        if (denuncia != null) {
            return objectMapper.convertValue(denuncia, DenunciaDTO.class);
        }
        throw new RegraDeNegocioException("Denúncia não encontrada com o ID fornecido.");
    }
}