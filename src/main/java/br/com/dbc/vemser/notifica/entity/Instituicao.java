package br.com.dbc.vemser.notifica.entity;

import br.com.dbc.vemser.notifica.entity.enums.TipoUsuario;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity(name = "INSTITUICAO")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Instituicao implements UserDetails {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="instituicao_sequence")
    @SequenceGenerator(name="instituicao_sequence", sequenceName="seq_instituicao", allocationSize = 1)
    @Column(name = "id_instituicao")
    private Integer idInstituicao;

    @Column(name = "nome_instituicao")
    private String nomeInstituicao;

    @Column(name = "email_instituicao")
    private String emailInstituicao;

    @Column(name = "celular_instituicao")
    private String celularInstituicao;

    @Column(name = "senha_instituicao")
    private String senhaInstituicao;

    @Column(name = "tipo_usuario")
    private TipoUsuario tipoUsuario;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "instituicao")
    private List<Avisos> avisos;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + tipoUsuario.name()));
    }

    @Override
    public String getPassword() {
        return senhaInstituicao;
    }

    @Override
    public String getUsername() {
        return emailInstituicao;
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
