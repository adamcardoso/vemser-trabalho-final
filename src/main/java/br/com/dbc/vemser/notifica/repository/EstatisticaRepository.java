package br.com.dbc.vemser.notifica.repository;

import br.com.dbc.vemser.notifica.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface EstatisticaRepository extends JpaRepository<Usuario, Integer> {

    //Retorna o Enum e a quantidade de vzs q ele aparece
    @Query("SELECT d.usuario.etniaUsuario, COUNT(d.usuario) FROM DENUNCIA d GROUP BY d.usuario.etniaUsuario")
    List<Object[]> countByEtnia();

    @Query("SELECT d.usuario.generoUsuario, COUNT(d.usuario) FROM DENUNCIA d GROUP BY d.usuario.generoUsuario")
    List<Object[]> countByGenero();

    @Query("SELECT d.usuario.classeSocial, COUNT(d.usuario) FROM DENUNCIA d GROUP BY d.usuario.classeSocial")
    List<Object[]> countByClasseSocial();

    @Query("SELECT MAX(c.dataHora) FROM DENUNCIA c")
    LocalDate ultimaCriacaoDenuncia();
}
