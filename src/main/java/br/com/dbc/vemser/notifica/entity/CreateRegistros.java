package br.com.dbc.vemser.notifica.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.time.LocalDateTime;
@Data
@Document
public class CreateRegistros {
    @Id
    Integer idCreateRegistro;
    LocalDateTime dataHora;
}
