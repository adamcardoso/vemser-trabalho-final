package br.com.dbc.vemser.notifica.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;

@Data
@Document(collection = "create_registros")
@AllArgsConstructor
@NoArgsConstructor
public class CreateRegistros {
    @Id
    private String idCreateRegistro;

    @Field("data_hora")
    private LocalDate dataHora;
}


