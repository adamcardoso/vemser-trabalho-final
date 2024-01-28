package br.com.dbc.vemser.notifica.controller;

import br.com.dbc.vemser.notifica.controller.documentacao.IEnderecoController;
import br.com.dbc.vemser.notifica.dto.endereco.CreateEnderecoDTO;
import br.com.dbc.vemser.notifica.dto.endereco.EnderecoDTO;
import br.com.dbc.vemser.notifica.dto.endereco.UpdateEnderecoDTO;
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
    public ResponseEntity<EnderecoDTO>  obterEnderecoById(@PathVariable Integer id) throws Exception {
        return new ResponseEntity<>(enderecoService.obterEnderecoById(id), HttpStatus.OK);
    }

    @GetMapping("/{id}/usuario")
    public ResponseEntity<List<EnderecoDTO>> obterEnderecosByIdUsuario(@PathVariable("id") Integer id) throws Exception {
        return new ResponseEntity<>(enderecoService.obterEnderecosByIdUsuario(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<EnderecoDTO> adicionarEndereco(@Valid @RequestBody CreateEnderecoDTO enderecoDto) throws Exception {
        EnderecoDTO enderecoAdicionado = enderecoService.adicionarEndereco(enderecoDto);
        return new ResponseEntity<>(enderecoAdicionado, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EnderecoDTO> atualizarEndereco(@PathVariable("id") Integer id,
                                                         @Valid @RequestBody UpdateEnderecoDTO enderecoDto) throws Exception {
        EnderecoDTO enderecoAtualizado = enderecoService.atualizarEndereco(id, enderecoDto);
        return new ResponseEntity<>(enderecoAtualizado, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> removerEndereco(@PathVariable("id") Integer id) throws Exception {
        enderecoService.removerEndereco(id);
        return ResponseEntity.ok().build();
    }
}
