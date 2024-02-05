package br.com.dbc.vemser.notifica.entity;

import br.com.dbc.vemser.notifica.entity.enums.TipoEndereco;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity(name = "ENDERECO")
public class Endereco {
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="endereco_sequence")
    @SequenceGenerator(name="endereco_sequence", sequenceName="seq_endereco", allocationSize = 1)
    @Column(name = "id_endereco")
    private Integer idEndereco;

    @Column(name = "id_usuario", insertable = false, updatable = false)
    private Integer idUsuario;

    @Column(name = "tipo_endereco")
    private TipoEndereco tipoEndereco;

    @Column(name = "logradouro")
    private String logradouro;

    @Column(name = "numero")
    private Integer numero;

    @Column(name = "complemento")
    private String complemento;

    @Column(name = "cep")
    private String cep;

    @Column(name = "cidade")
    private String cidade;

    @Column(name = "estado")
    private String estado;

    @Column(name = "pais")
    private String pais;

    @ManyToOne
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    private Usuario usuario;
}
