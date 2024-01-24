package br.com.dbc.vemser.notifica.dto.denuncia;

import br.com.dbc.vemser.notifica.entity.Localizacao;
import br.com.dbc.vemser.notifica.entity.Usuario;
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
        @NotNull(message = "O Id não pode ser nulo!")
        private int idDenuncia;
        private String descricao;
        @NotNull(message = "A Denúncia precisa ter um Título!")
        private String titulo;
        //private Localizacao local;
        //private LocalDateTime dataHora;
        private StatusDenuncia statusDenuncia;
        private int curtidas;
        private Categoria categoria;
        //private int curtidas;
        //private final List<String> comentarios = new ArrayList<>();
        private TipoDenuncia tipoDenuncia;
        private int idUsuario;


        public DenunciaDTO(int idDenuncia, int idUsuario, String descricao, String titulo, StatusDenuncia statusDenuncia, Categoria categoria, TipoDenuncia tipoDenuncia) {
                this.idDenuncia = idDenuncia;
                this.idUsuario = idUsuario;
                this.descricao = descricao;
                this.titulo = titulo;
                this.statusDenuncia = statusDenuncia;
                this.categoria = categoria;
                this.tipoDenuncia = tipoDenuncia;
        }
}
