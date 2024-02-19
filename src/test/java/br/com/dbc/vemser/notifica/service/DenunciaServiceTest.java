package br.com.dbc.vemser.notifica.service;

import br.com.dbc.vemser.notifica.dto.denuncia.DenunciaCreateDTO;
import br.com.dbc.vemser.notifica.dto.denuncia.DenunciaDTO;
import br.com.dbc.vemser.notifica.dto.localizacao.LocalizacaoCreateDTO;
import br.com.dbc.vemser.notifica.entity.Denuncia;
import br.com.dbc.vemser.notifica.entity.Localizacao;
import br.com.dbc.vemser.notifica.entity.Usuario;
import br.com.dbc.vemser.notifica.entity.enums.Categoria;
import br.com.dbc.vemser.notifica.entity.enums.StatusDenuncia;
import br.com.dbc.vemser.notifica.entity.enums.TipoDenuncia;
import br.com.dbc.vemser.notifica.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.notifica.repository.DenunciaRepository;
import br.com.dbc.vemser.notifica.repository.UsuarioRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DenunciaServiceTest {
    @Mock
    private DenunciaRepository denunciaRepository;
    @Mock
    private UsuarioRepository usuarioRepository;
    @Mock
    private ObjectMapper objectMapper;
    @InjectMocks
    private DenunciaService denunciaService;

    @Test
    void listByIdUsuarioComSucesso() throws Exception {
        int idUsuario = 1;
        Denuncia denuncia1 = new Denuncia();
        denuncia1.setIdDenuncia(1);
        denuncia1.setIdUsuario(idUsuario);
        Denuncia denuncia2 = new Denuncia();
        denuncia2.setIdDenuncia(2);
        denuncia2.setIdUsuario(idUsuario);

        List<Denuncia> denuncias = Arrays.asList(denuncia1, denuncia2);

        when(denunciaRepository.getDenunciaAtivaByIdUsuario(idUsuario)).thenReturn(denuncias);

        List<DenunciaDTO> resultado = denunciaService.listByIdUsuario(idUsuario);

        verify(denunciaRepository, times(1)).getDenunciaAtivaByIdUsuario(eq(idUsuario));

        assertEquals(denuncias.size(), resultado.size());
    }

    @Test
    void criarDenunciaComSucesso() throws Exception {
        when(usuarioRepository.findById(anyInt())).thenReturn(java.util.Optional.of(new Usuario()));
        when(denunciaRepository.save(any())).thenReturn(new Denuncia());
        when(objectMapper.convertValue(any(), eq(Denuncia.class))).thenReturn(new Denuncia());

        int idUsuario = 1;
        DenunciaCreateDTO denunciaCreateDTO = new DenunciaCreateDTO();
        denunciaCreateDTO.setTitulo("Test Title");
        denunciaCreateDTO.setDescricao("Test Description");
        denunciaCreateDTO.setStatusDenuncia(StatusDenuncia.ABERTO);
        denunciaCreateDTO.setCategoria(Categoria.AGUA_POTAVEL);
        denunciaCreateDTO.setTipoDenuncia(TipoDenuncia.ANONIMA);
        LocalizacaoCreateDTO localizacaoCreateDTO = new LocalizacaoCreateDTO();
        localizacaoCreateDTO.setLatitude("123");
        localizacaoCreateDTO.setLongitude("321");
        denunciaCreateDTO.setLocalizacao(localizacaoCreateDTO);

        DenunciaDTO resultado = denunciaService.criarDenuncia(denunciaCreateDTO, idUsuario);

        verify(denunciaRepository, times(1)).save(any());
        verify(objectMapper, times(1)).convertValue(any(), eq(Denuncia.class));

        //assertEquals(denunciaCreateDTO, resultado);

    }

    @Test
    void editarDenunciaComSucesso() throws RegraDeNegocioException {
        int idDenuncia = 1;
        int idUsuario = 1;

        DenunciaCreateDTO denunciaCreateDTO = new DenunciaCreateDTO();
        denunciaCreateDTO.setTitulo("New Title");
        denunciaCreateDTO.setDescricao("New Description");
        denunciaCreateDTO.setStatusDenuncia(StatusDenuncia.FECHADO);
        denunciaCreateDTO.setCategoria(Categoria.AGUA_POTAVEL);
        denunciaCreateDTO.setTipoDenuncia(TipoDenuncia.PUBLICA);

        Denuncia denunciaExistente = new Denuncia();
        denunciaExistente.setIdDenuncia(idDenuncia);
        denunciaExistente.setIdUsuario(idUsuario);

        Usuario usuario = new Usuario();
        usuario.setIdUsuario(idUsuario);

        when(usuarioRepository.findById(Mockito.eq(idUsuario))).thenReturn(Optional.of(usuario));
        when(denunciaRepository.getDenunciaAtiva(Mockito.eq(idDenuncia))).thenReturn(denunciaExistente);
        when(denunciaRepository.save(any(Denuncia.class))).thenReturn(denunciaExistente);
        when(objectMapper.convertValue(eq(denunciaExistente), eq(DenunciaDTO.class))).thenReturn(new DenunciaDTO());

        DenunciaDTO resultado = denunciaService.editarDenuncia(denunciaCreateDTO, idDenuncia, idUsuario);

        verify(usuarioRepository, times(1)).findById(eq(idUsuario));
        verify(denunciaRepository, times(1)).getDenunciaAtiva(eq(idDenuncia));
        verify(denunciaRepository, times(1)).save(any(Denuncia.class));
        verify(objectMapper, times(1)).convertValue(eq(denunciaExistente),eq(DenunciaDTO.class));
    }

    @Test
    void deletarDenunciaComSucesso() throws RegraDeNegocioException {
        Integer idAleatorioDenuncia = new Random().nextInt();
        Integer idAleatorioUsuario = new Random().nextInt();

        Denuncia denunciaExistente = new Denuncia();
        denunciaExistente.setIdDenuncia(idAleatorioDenuncia);
        denunciaExistente.setIdUsuario(idAleatorioUsuario);
        denunciaExistente.setTitulo("New Title");
        denunciaExistente.setDescricao("New Description");
        denunciaExistente.setStatusDenuncia(StatusDenuncia.FECHADO);
        denunciaExistente.setCategoria(Categoria.AGUA_POTAVEL);
        denunciaExistente.setTipoDenuncia(TipoDenuncia.PUBLICA);

        Usuario usuario = new Usuario();
        usuario.setIdUsuario(idAleatorioUsuario);

        when(denunciaRepository.getDenunciaAtiva(eq(idAleatorioDenuncia))).thenReturn(denunciaExistente);
        when(denunciaRepository.save(any(Denuncia.class))).thenReturn(denunciaExistente);

        denunciaService.deletarDenuncia(idAleatorioDenuncia, idAleatorioUsuario);

        verify(denunciaRepository, times(1)).getDenunciaAtiva(eq(idAleatorioDenuncia));
        verify(denunciaRepository, times(1)).save(any(Denuncia.class));

        assertEquals(StatusDenuncia.FECHADO, denunciaExistente.getStatusDenuncia());
    }


    @Test
    void getDenuncia() throws RegraDeNegocioException {
        int idDenuncia = 1;
        Denuncia denunciaExistente = new Denuncia();
        denunciaExistente.setIdDenuncia(idDenuncia);

        when(denunciaRepository.getDenunciaAtiva(idDenuncia)).thenReturn(denunciaExistente);
        Denuncia result = denunciaService.getDenuncia(idDenuncia);

        assertNotNull(result);
        assertEquals(denunciaExistente, result);
    }

    @Test
    void deveriaFalharAoBuscarDenunciaInexistente() {
        int idDenuncia = 1;

        when(denunciaRepository.getDenunciaAtiva(idDenuncia)).thenReturn(null);

        assertThrows(RegraDeNegocioException.class,
                () -> denunciaService.getDenuncia(idDenuncia),
                "Denuncia não encontrada!");
    }

    @Test
    void deveriaRetornarListaDeDenunciasPorIdUsuario() throws RegraDeNegocioException {
        int idUsuario = 1;
        List<Denuncia> denuncias = new ArrayList<>();
        denuncias.add(new Denuncia());

        when(denunciaRepository.getDenunciaAtivaByIdUsuario(idUsuario)).thenReturn(denuncias);

        List<Denuncia> result = denunciaService.getDenunciasByIdUsuario(idUsuario);

        assertNotNull(result);
        assertFalse(result.isEmpty());
    }

    @Test
    void deveriaFalharAoBuscarDenunciasPorIdUsuarioSemResultados() {
        int idUsuario = 1;

        when(denunciaRepository.getDenunciaAtivaByIdUsuario(idUsuario)).thenReturn(new ArrayList<>());

        assertThrows(RegraDeNegocioException.class,
                () -> denunciaService.getDenunciasByIdUsuario(idUsuario),
                "Nenhuma denuncia encontrada!");
    }
}
