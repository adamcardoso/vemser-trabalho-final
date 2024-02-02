package br.com.dbc.vemser.notifica.service;

import br.com.dbc.vemser.notifica.dto.estatistica.EstatisticaDTO;
import br.com.dbc.vemser.notifica.entity.Estatistica;
import br.com.dbc.vemser.notifica.repository.EstatisticaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@RequiredArgsConstructor
@Service
public class EstatisticaService {
    private final EstatisticaRepository estatisticaRepository;
    private final ObjectMapper objectMapper;

//    public Optional<HashMap<String, List<EstatisticaDTO>>> obterEstatistica(List<String> colunas) {
//
//    }
}
