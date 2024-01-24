package br.com.dbc.vemser.notifica.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
public class Response<T> {
    public int status;
    public String mensagem;
    public LocalDateTime timestamp;
    public T data;

    public Response(T data, int status, String mensagem){
        this.status = status;
        this.mensagem = mensagem;
        this.timestamp = LocalDateTime.now();
        this.data = data;
    }
}
