package br.com.dbc.vemser.notifica.entity;

import br.com.dbc.vemser.notifica.dto.endereco.EnderecoCreateDTO;
import br.com.dbc.vemser.notifica.entity.enums.TipoEndereco;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

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

    @Column(name = "bairro")
    private String bairro;

    @Column(name = "cep")
    private String cep;

    @Column(name = "cidade")
    private String cidade;

    @Column(name = "estado")
    private String estado;

    @Column(name = "pais")
    private String pais;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    private Usuario usuario;

    @Column(name = "latitude ")
    private String latitude;

    @Column(name = "longitude ")
    private String longitude;

    public Endereco(Integer idEndereco, Integer idUsuario, TipoEndereco tipoEndereco, String logradouro, Integer numero, String complemento, String cep, String bairro, String cidade, String estado, String pais, Usuario usuario) {
        this.idEndereco = idEndereco;
        this.idUsuario = idUsuario;
        this.tipoEndereco = tipoEndereco;
        this.logradouro = logradouro;
        this.numero = numero;
        this.complemento = complemento;
        this.cep = cep;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.pais = pais;
        this.usuario = usuario;
    }

    public Endereco(Integer idUsuario, EnderecoCreateDTO enderecoCreateDTO) {
        this.idUsuario = idUsuario;
        this.tipoEndereco = enderecoCreateDTO.getTipoEndereco();
        this.logradouro = enderecoCreateDTO.getLogradouro();
        this.numero = enderecoCreateDTO.getNumero();
        this.complemento = enderecoCreateDTO.getComplemento();
        this.cep = enderecoCreateDTO.getCep();
        this.bairro = enderecoCreateDTO.getBairro();
        this.cidade = enderecoCreateDTO.getCidade();
        this.estado = enderecoCreateDTO.getEstado();
        this.pais = enderecoCreateDTO.getPais();

        this.latitude = enderecoCreateDTO.getLatitude();
        this.longitude = enderecoCreateDTO.getLongitude();
    }

}
