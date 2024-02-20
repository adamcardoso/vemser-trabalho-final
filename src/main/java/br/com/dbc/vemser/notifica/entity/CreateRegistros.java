package br.com.dbc.vemser.notifica.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;

@Data
@Document(collection = "create_registros")
public class CreateRegistros {
    @Id
    private String idCreateRegistro;

    @Field("data_hora")
    private LocalDate dataHora;
}
