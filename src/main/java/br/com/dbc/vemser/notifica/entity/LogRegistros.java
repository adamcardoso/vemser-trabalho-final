package br.com.dbc.vemser.notifica.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
@Document(collection = "log_registros")
public class LogRegistros {
    @Id
    private Integer idLogRegistro;

    @Field("data_hora")
    private LocalDateTime dataHora;
}
