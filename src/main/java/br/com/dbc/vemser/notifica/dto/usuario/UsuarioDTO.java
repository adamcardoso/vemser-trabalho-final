    package br.com.dbc.vemser.notifica.dto.usuario;

    import br.com.dbc.vemser.notifica.dto.comentario.ComentarioDTO;
    import br.com.dbc.vemser.notifica.dto.denuncia.DenunciaDTO;
    import br.com.dbc.vemser.notifica.dto.endereco.EnderecoDTO;
    import br.com.dbc.vemser.notifica.entity.Comentario;
    import br.com.dbc.vemser.notifica.entity.Denuncia;
    import br.com.dbc.vemser.notifica.entity.Endereco;
    import br.com.dbc.vemser.notifica.entity.enums.ClasseSocial;
    import br.com.dbc.vemser.notifica.entity.enums.Etnia;
    import br.com.dbc.vemser.notifica.entity.enums.Genero;
    import br.com.dbc.vemser.notifica.entity.enums.TipoUsuario;
    import com.fasterxml.jackson.annotation.JsonIgnore;
    import lombok.*;

    import java.time.LocalDate;
    import java.util.List;

    @NoArgsConstructor
    @Getter
    @Setter
    public class UsuarioDTO {
        private Integer idUsuario;
        private String nomeUsuario;
        private String emailUsuario;
        private String numeroCelular;
        private Etnia etniaUsuario;
        private LocalDate dataNascimento;
        private ClasseSocial classeSocial;
        private Genero generoUsuario;
        @JsonIgnore
        List<EnderecoDTO> enderecos;
        @JsonIgnore
        List<ComentarioDTO> comentarios;
        @JsonIgnore
        List<DenunciaDTO> denuncias;

        public UsuarioDTO(Integer idUsuario, String nomeUsuario, String emailUsuario, String numeroCelular, Etnia etniaUsuario, LocalDate dataNascimento, ClasseSocial classeSocial, Genero generoUsuario) {
            this.idUsuario = idUsuario;
            this.nomeUsuario = nomeUsuario;
            this.emailUsuario = emailUsuario;
            this.numeroCelular = numeroCelular;
            this.etniaUsuario = etniaUsuario;
            this.dataNascimento = dataNascimento;
            this.classeSocial = classeSocial;
            this.generoUsuario = generoUsuario;
        }

    }
