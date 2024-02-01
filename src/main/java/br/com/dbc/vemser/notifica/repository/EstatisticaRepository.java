package br.com.dbc.vemser.notifica.repository;

import br.com.dbc.vemser.notifica.dto.estatistica.EstatisticaDTO;
import br.com.dbc.vemser.notifica.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EstatisticaRepository extends JpaRepository<Usuario, Integer> {

//    @Query("""
//            SELECT
//                u.etniaUsuario as etniaUsuario,
//                u.classeSocial as classeSocial,
//                u.generoUsuario as generoUsuario,
//                COUNT(d.idDenuncia) AS total,
//                COUNT(d.idDenuncia) / (SELECT COUNT(d2.idDenuncia) FROM Denuncia d2) * 100 AS percentual,
//                MAX(d.dataHora) as ultimaDataCadastro
//            FROM
//                Usuario u
//                LEFT JOIN Denuncia d ON u.idUsuario = d.idUsuario
//            GROUP BY
//                u.etniaUsuario, u.classeSocial, u.generoUsuario
//            ORDER BY
//                u.etniaUsuario ASC, u.classeSocial ASC, u.generoUsuario ASC
//            """)
//    List<EstatisticaDTO> obterEstatistica();
}
