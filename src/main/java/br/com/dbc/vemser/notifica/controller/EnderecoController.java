package br.com.dbc.vemser.notifica.controller;

import br.com.dbc.vemser.notifica.controller.documentacao.IEnderecoController;
import br.com.dbc.vemser.notifica.dto.endereco.EnderecoCreateDTO;
import br.com.dbc.vemser.notifica.dto.endereco.EnderecoDTO;
import br.com.dbc.vemser.notifica.dto.endereco.EnderecoUpdateDTO;
import br.com.dbc.vemser.notifica.exceptions.Response;
import br.com.dbc.vemser.notifica.service.IService.IEnderecoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/endereco")
public class EnderecoController implements IEnderecoController {
    private final IEnderecoService enderecoService;
    @GetMapping("/{id}")
    public ResponseEntity<Response<EnderecoDTO>> obterEnderecoById(@PathVariable Integer id){
        try{
            return enderecoService.obterEnderecoById(id)
                    .map(e -> new ResponseEntity<>(new Response<>(e, 200, "Endereço obtido com sucesso!"), HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(new Response<>(null, 404, "Endereço não encontrado"), HttpStatus.NOT_FOUND));
        } catch (Exception e){
            return new ResponseEntity<>(
                    new Response<>(null, 400, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}/usuario")
    public ResponseEntity<Response<List<EnderecoDTO>>> obterEnderecosByIdUsuario(@PathVariable("id") Integer id){
        try{
            return enderecoService.obterEnderecosByIdUsuario(id)
                    .map(e -> new ResponseEntity<>(new Response<>(e, 200, "Endereços obtidos com sucesso!"), HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(new Response<>(null, 404, "Usuário não encontrado!"), HttpStatus.NOT_FOUND));
        } catch (Exception e){
            return new ResponseEntity<>(
                    new Response<>(null, 400, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    public ResponseEntity<Response<EnderecoDTO>> adicionarEndereco(@RequestBody EnderecoCreateDTO enderecoDto) {
        try{
            return enderecoService.adicionarEndereco(enderecoDto)
                    .map(c -> new ResponseEntity<>(new Response<>(c, 201, "Endereço criado com sucesso!"), HttpStatus.CREATED))
                    .orElseGet(() -> new ResponseEntity<>(new Response<>(null, 400, "Ocorreu um problema!"), HttpStatus.BAD_REQUEST));
        } catch(Exception e){
            return new ResponseEntity<>(new Response<>(null, 400, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response<EnderecoDTO>> atualizarEndereco(@PathVariable("id") Integer id, @RequestBody EnderecoUpdateDTO enderecoDto){
        try{
            return enderecoService.atualizarEndereco(id, enderecoDto)
                    .map(e -> new ResponseEntity<>(
                            new Response<>(e, 200, "Endereço atualizado com sucesso!"), HttpStatus.CREATED))
                    .orElseGet(() -> new ResponseEntity<>(
                            new Response<>(null, 404, "Endereço não encontrado!"), HttpStatus.NOT_FOUND));
        } catch(Exception e){
            return new ResponseEntity<>(
                    new Response<>(null, 400, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response<Object>> removerEndereco(@PathVariable("id") Integer id) {
        try{
            return enderecoService.removerEndereco(id)
                    .map(e -> new ResponseEntity<>(new Response<>(null, 200, "Endereço deletado com sucesso!"), HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(new Response<>(null, 404, "Endereço não encontrado!"), HttpStatus.NOT_FOUND));
        } catch(Exception e){
            return new ResponseEntity<>(
                    new Response<>(null, 400, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
}
