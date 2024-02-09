package br.com.dbc.vemser.notifica.controller;

import br.com.dbc.vemser.notifica.controller.documentacao.IAdminController;
import br.com.dbc.vemser.notifica.dto.denuncia.DenunciaDTO;
import br.com.dbc.vemser.notifica.dto.usuario.UsuarioCreateDTO;
import br.com.dbc.vemser.notifica.dto.usuario.UsuarioUpdateDTO;
import br.com.dbc.vemser.notifica.dto.usuario.UsuarioDTO;
import br.com.dbc.vemser.notifica.service.AdminService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/admin")
@Validated
@AllArgsConstructor
@Tag(name = "Admin Controller")
public class AdminController implements IAdminController {
    private final AdminService adminService;

    @GetMapping("/list-usuarios-ativos")
    public ResponseEntity<Page<UsuarioDTO>> listarUsuariosPaginadosAtivos(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<UsuarioDTO> usuarios = adminService.listUsuariosAtivos(pageable);

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
    @GetMapping("/list-usuarios-inativos")
    public ResponseEntity<Page<UsuarioDTO>> listarUsuariosPaginadosInativos(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<UsuarioDTO> usuarios = adminService.listUsuariosInativos(pageable);

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

    @GetMapping("/list-admin")
    public ResponseEntity<List<UsuarioDTO>> listarAdmins() {
        List<UsuarioDTO> usuarios = adminService.listUsuariosAdmin();
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }

    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<UsuarioDTO> obterUsuarioById(@PathVariable("idUsuario") Integer idUsuario) throws Exception {
        UsuarioDTO usuario = adminService.obterUsuarioById(idUsuario);
        return new ResponseEntity<>(usuario, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UsuarioDTO> criarUsuarioAdmin(@Valid @RequestBody UsuarioCreateDTO novoUsuario) throws Exception {
        UsuarioDTO usuarios = adminService.criarUsuario(novoUsuario);
        return new ResponseEntity<>(usuarios, HttpStatus.CREATED);
    }

    @PutMapping("/{idUsuario}")
    public ResponseEntity<UsuarioDTO> atualizarAdmin(@PathVariable("idUsuario") Integer idUsuario, @Valid @RequestBody UsuarioUpdateDTO novoUsuario) throws Exception {
        UsuarioDTO usuarios = adminService.atualizarUsuario(idUsuario, novoUsuario);
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }

    @DeleteMapping("/usuario/{idUsuario}")
    public ResponseEntity<Object> removerUsuario(@PathVariable("idUsuario") Integer idUsuario) throws Exception {
        adminService.removerUsuario(idUsuario);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/denuncia/{idDenuncia}")
    public ResponseEntity<DenunciaDTO> obterDenunciaById(@PathVariable("idDenuncia") Integer idDenuncia) throws Exception {
        DenunciaDTO denunciaDTO = adminService.denunciaById(idDenuncia);
        return ResponseEntity.ok(denunciaDTO);
    }

    @GetMapping("/list-denuncias-ativas")
    public ResponseEntity<List<DenunciaDTO>> listarTodasDenunciasAtivas() throws Exception {
        List<DenunciaDTO> denunciaDTOS = adminService.listarTodasDenunciasAtivas() ;
        return ResponseEntity.ok(denunciaDTOS);
    }

    @DeleteMapping("/denuncia/{idDenuncia}")
    public ResponseEntity<Object> deletarDenuncia(@PathVariable Integer idDenuncia) throws Exception {
        adminService.deletarDenuncia(idDenuncia);
        return ResponseEntity.noContent().build();
    }
}