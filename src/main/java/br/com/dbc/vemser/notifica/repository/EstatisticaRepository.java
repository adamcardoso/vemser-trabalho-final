package br.com.dbc.vemser.notifica.repository;

import br.com.dbc.vemser.notifica.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface EstatisticaRepository extends JpaRepository<Usuario, Integer> {

    // Retorna o Enum e a quantidade de vezes que ele aparece para usuários distintos
    @Query("SELECT u.etniaUsuario, COUNT(u) FROM USUARIO u WHERE u IN (SELECT DISTINCT d.usuario FROM DENUNCIA d) GROUP BY u.etniaUsuario")
    List<Object[]> countByEtnia();

    @Query("SELECT u.generoUsuario, COUNT(u) FROM USUARIO u WHERE u IN (SELECT DISTINCT d.usuario FROM DENUNCIA d) GROUP BY u.generoUsuario")
    List<Object[]> countByGenero();

    @Query("SELECT u.classeSocial, COUNT(u) FROM USUARIO u WHERE u IN (SELECT DISTINCT d.usuario FROM DENUNCIA d) GROUP BY u.classeSocial")
    List<Object[]> countByClasseSocial();

    @Query("SELECT MAX(d.dataHora) FROM DENUNCIA d")
    LocalDate ultimaCriacaoDenuncia();
}
