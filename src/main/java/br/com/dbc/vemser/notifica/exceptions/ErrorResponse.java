package br.com.dbc.vemser.notifica.exceptions;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {

    @ApiModelProperty(notes = "Timestamp da resposta de erro")
    private String timestamp;

    @ApiModelProperty(notes = "Código de status HTTP da resposta de erro")
    private int status;

    @ApiModelProperty(notes = "Mensagem de erro específica")
    private String message;

    @ApiModelProperty(notes = "Lista de mensagens de erro")
    private List<String> errors;
}
