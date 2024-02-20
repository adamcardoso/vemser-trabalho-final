package br.com.dbc.vemser.notifica.entity;

import br.com.dbc.vemser.notifica.entity.enums.TipoRegistro;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDate;

@Data
@Document(collection = "denuncia_registros")
@AllArgsConstructor
@NoArgsConstructor
public class DenunciaRegistros {
    @Id
    private String idLogRegistro;
    @Field("data_hora")
    private LocalDate dataHora;
    @Enumerated(EnumType.STRING)
    @Field("tipo_registro")
    private TipoRegistro tipoRegistro;
}
