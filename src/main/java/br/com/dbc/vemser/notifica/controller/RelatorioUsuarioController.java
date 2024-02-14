package br.com.dbc.vemser.notifica.controller;

import br.com.dbc.vemser.notifica.dto.relatorio.RelatorioDto;
import br.com.dbc.vemser.notifica.dto.usuario.UsuarioDTO;
import br.com.dbc.vemser.notifica.entity.Usuario;
import br.com.dbc.vemser.notifica.service.RelatorioUsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/relatorio")
public class RelatorioUsuarioController {
    private final RelatorioUsuarioService relatorioUsuarioService;

    @GetMapping("/por-estado")
    public ResponseEntity<Page<UsuarioDTO>> usuarioPorEstado(@RequestParam("estado") String estado, @PageableDefault(size = 10, page = 0) Pageable pageable){
        return new ResponseEntity<>(relatorioUsuarioService.usuariosPorEstado(estado, pageable), HttpStatus.OK);
    }

    @GetMapping("/quantidade-curtidas")
    public ResponseEntity<Page<RelatorioDto>> obterCurtidasUsuarios(@PageableDefault(size = 10, page = 0) Pageable pageable){
        return new ResponseEntity<>(relatorioUsuarioService.obterCurtidasUsuarios(pageable), HttpStatus.OK);
    }

    @GetMapping("/por-cidade")
    public ResponseEntity<Page<UsuarioDTO>> usuarioPorCidade(@RequestParam("cidade") String cidade, @PageableDefault(size = 10, page = 0) Pageable pageable){
        return new ResponseEntity<>(relatorioUsuarioService.usuariosPorCidade(cidade, pageable), HttpStatus.OK);
    }
}
