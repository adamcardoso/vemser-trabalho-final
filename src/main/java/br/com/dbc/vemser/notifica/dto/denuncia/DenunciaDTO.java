package br.com.dbc.vemser.notifica.dto.denuncia;

import br.com.dbc.vemser.notifica.entity.Localizacao;
import br.com.dbc.vemser.notifica.entity.enums.Categoria;
import br.com.dbc.vemser.notifica.entity.enums.StatusDenuncia;
import br.com.dbc.vemser.notifica.entity.enums.TipoDenuncia;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DenunciaDTO {
        private Integer idDenuncia;
        private String descricao;
        private String titulo;
        //private Localizacao local;
        //private LocalDateTime dataHora;
        private StatusDenuncia statusDenuncia;
        private Integer curtidas;
        private Categoria categoria;
        //private final List<String> comentarios = new ArrayList<>();
        private TipoDenuncia tipoDenuncia;
        private Integer idUsuario;


        public DenunciaDTO(Integer idDenuncia, Integer idUsuario, String descricao, String titulo, Integer curtidas, StatusDenuncia statusDenuncia, Categoria categoria, TipoDenuncia tipoDenuncia) {
                this.idDenuncia = idDenuncia;
                this.idUsuario = idUsuario;
                this.descricao = descricao;
                this.titulo = titulo;
                this.curtidas = curtidas;
                this.statusDenuncia = statusDenuncia;
                this.categoria = categoria;
                this.tipoDenuncia = tipoDenuncia;
        }
}
