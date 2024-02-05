package br.com.dbc.vemser.notifica.controller;

import br.com.dbc.vemser.notifica.dto.usuario.UsuarioDTO;
import br.com.dbc.vemser.notifica.entity.Usuario;
import br.com.dbc.vemser.notifica.repository.AdminRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/paginacao")
@Tag(name = "Denúncia Controller")
public class PaginacaoController {
    private final AdminRepository adminRepository;

    @GetMapping("/listar-usuarios")
    public ResponseEntity<Page<UsuarioDTO>> listarUsuariosPaginados(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<Usuario> usuarios = adminRepository.findAll(pageable);

        Page<UsuarioDTO> usuarioDTOs = usuarios.map(usuario -> new UsuarioDTO(
                usuario.getIdUsuario(),
                usuario.getNomeUsuario(),
                usuario.getEmailUsuario(),
                usuario.getNumeroCelular(),
                usuario.getEtniaUsuario(),
                usuario.getDataNascimento(),
                usuario.getClasseSocial(),
                usuario.getGeneroUsuario()
        ));

        return ResponseEntity.ok(usuarioDTOs);
    }
}
