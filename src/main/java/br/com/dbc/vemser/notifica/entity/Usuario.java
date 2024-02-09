package br.com.dbc.vemser.notifica.entity;

import br.com.dbc.vemser.notifica.entity.enums.ClasseSocial;
import br.com.dbc.vemser.notifica.entity.enums.Etnia;
import br.com.dbc.vemser.notifica.entity.enums.Genero;
import br.com.dbc.vemser.notifica.entity.enums.TipoUsuario;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.*;
import org.hibernate.FetchMode;
import org.hibernate.annotations.Fetch;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "USUARIO")
@Table(name = "Usuario")
public class Usuario implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USUARIO_SEQ")
    @SequenceGenerator(name = "USUARIO_SEQ", sequenceName = "SEQ_USUARIO", allocationSize = 1)
    @Column(name = "id_usuario")
    private Integer idUsuario;

    @Column(name = "nome_usuario")
    private String nomeUsuario;

    @Column(name = "email_usuario")
    private String emailUsuario;

    @Column(name = "celular_usuario")
    private String numeroCelular;

    @Column(name = "senha_usuario")
    private String senhaUsuario;

    @Column(name = "etnia")
    @Enumerated(EnumType.ORDINAL)
    private Etnia etniaUsuario;

    @Column(name = "data_nascimento")
    private LocalDate dataNascimento;

    @Column(name = "classe_social")
    @Enumerated(EnumType.ORDINAL)
    private ClasseSocial classeSocial;

    @Column(name = "genero")
    @Enumerated(EnumType.ORDINAL)
    private Genero generoUsuario;

    @Column(name = "tipo_usuario")
    @Enumerated(EnumType.ORDINAL)
    private TipoUsuario tipoUsuario;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    List<Endereco> enderecos;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    List<Comentario> comentarios;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    List<Denuncia> denuncias;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + tipoUsuario.name()));
        return authorities;
    }

    @Override
    public String getPassword() {
        return senhaUsuario;
    }

    @Override
    public String getUsername() {
        return emailUsuario;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
