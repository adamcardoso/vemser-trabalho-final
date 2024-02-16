package br.com.dbc.vemser.notifica.service;

import br.com.dbc.vemser.notifica.dto.estatistica.EstatisticaDTO;
import br.com.dbc.vemser.notifica.entity.enums.ClasseSocial;
import br.com.dbc.vemser.notifica.entity.enums.Etnia;
import br.com.dbc.vemser.notifica.entity.enums.Genero;
import br.com.dbc.vemser.notifica.repository.EstatisticaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@DisplayName("EstatisticaService - Test")
class EstatisticaServiceTest {

    @Mock
    private EstatisticaRepository estatisticaRepository;

    @InjectMocks
    private EstatisticaService estatisticaService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Testar geração de estatísticas com dados de exemplo")
    void testGerarEstatisticasComDadosDeExemplo() {
        when(estatisticaRepository.countByEtnia()).thenReturn(
                Arrays.asList(
                        new Object[]{Etnia.BRANCO, 1L},
                        new Object[]{Etnia.PRETO, 2L},
                        new Object[]{Etnia.PARDO, 1L}
                )
        );

        when(estatisticaRepository.countByGenero()).thenReturn(
                Arrays.asList(
                        new Object[]{Genero.MASCULINO, 1L},
                        new Object[]{Genero.FEMININO, 2L},
                        new Object[]{Genero.OUTRO, 1L}
                )
        );

        when(estatisticaRepository.countByClasseSocial()).thenReturn(
                Arrays.asList(
                        new Object[]{ClasseSocial.C, 1L},
                        new Object[]{ClasseSocial.D, 1L},
                        new Object[]{ClasseSocial.E, 2L}
                )
        );

        when(estatisticaRepository.ultimaCriacaoDenuncia()).thenReturn(LocalDate.parse("2024-02-14"));

        EstatisticaDTO result = estatisticaService.gerarEstatisticas();

        assertEquals("2024-02-14", result.getUltimaDataCadastro().toString());

        Map<Etnia, Double> percentagensEtnia = result.getEtniaUsuario();
        assertEquals(25.0, percentagensEtnia.get(Etnia.BRANCO));
        assertEquals(50.0, percentagensEtnia.get(Etnia.PRETO));
        assertEquals(25.0, percentagensEtnia.get(Etnia.PARDO));

        Map<Genero, Double> percentagensGenero = result.getGeneroUsuario();
        assertEquals(25.0, percentagensGenero.get(Genero.MASCULINO));
        assertEquals(50.0, percentagensGenero.get(Genero.FEMININO));
        assertEquals(25.0, percentagensGenero.get(Genero.OUTRO));

        Map<ClasseSocial, Double> percentagensClasseSocial = result.getClasseSocial();
        assertEquals(25.0, percentagensClasseSocial.get(ClasseSocial.C));
        assertEquals(25.0, percentagensClasseSocial.get(ClasseSocial.D));
        assertEquals(50.0, percentagensClasseSocial.get(ClasseSocial.E));
    }
    
}
