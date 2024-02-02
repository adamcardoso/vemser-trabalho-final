package br.com.dbc.vemser.notifica.controller;

import br.com.dbc.vemser.notifica.controller.documentacao.IEnderecoController;
import br.com.dbc.vemser.notifica.dto.endereco.EnderecoCreateDTO;
import br.com.dbc.vemser.notifica.dto.endereco.EnderecoDTO;
import br.com.dbc.vemser.notifica.dto.endereco.EnderecoUpdateDTO;
import br.com.dbc.vemser.notifica.service.EnderecoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/endereco")
public class EnderecoController implements IEnderecoController {
    private final EnderecoService enderecoService;
    @GetMapping("/{id}")
    public ResponseEntity<EnderecoDTO>  obterEnderecoById(@PathVariable("id") Integer id) throws Exception {
        return new ResponseEntity<>(enderecoService.obterEnderecoById(id), HttpStatus.OK);
    }

    @GetMapping("/{id}/usuario")
    public ResponseEntity<List<EnderecoDTO>> obterEnderecosByIdUsuario(@PathVariable("id") Integer id) throws Exception {
        return new ResponseEntity<>(enderecoService.obterEnderecosByIdUsuario(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<EnderecoDTO> adicionarEndereco(@Valid @RequestBody EnderecoCreateDTO enderecoDto) throws Exception {
        return new ResponseEntity<>(enderecoService.adicionarEndereco(enderecoDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EnderecoDTO> atualizarEndereco(@PathVariable("id") Integer id,
                                                         @Valid @RequestBody EnderecoUpdateDTO enderecoDto) throws Exception {
        return new ResponseEntity<>(enderecoService.atualizarEndereco(id, enderecoDto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerEndereco(@PathVariable("id") Integer id) throws Exception {
        enderecoService.removerEndereco(id);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }
}