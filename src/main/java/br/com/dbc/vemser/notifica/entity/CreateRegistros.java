package br.com.dbc.vemser.notifica.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
@Document(collection = "create_registros")
public class CreateRegistros {
    @Id
    private Integer idCreateRegistro;

    @Field("data_hora")
    private LocalDateTime dataHora;
}
