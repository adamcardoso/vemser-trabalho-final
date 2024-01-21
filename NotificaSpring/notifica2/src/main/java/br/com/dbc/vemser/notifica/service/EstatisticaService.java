package br.com.dbc.vemser.notifica.service;

import br.com.dbc.vemser.notifica.dto.estatistica.EstatisticaDto;
import br.com.dbc.vemser.notifica.entity.Estatistica;
import br.com.dbc.vemser.notifica.repository.EstatisticaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.*;

@RequiredArgsConstructor
@Service
public class EstatisticaService {
    private final EstatisticaRepository estatisticaRepository;
    private final ObjectMapper objectMapper;

    public Optional<HashMap<String, List<EstatisticaDto>>> obterEstatistica(List<String> colunas) throws Exception{
        try {
            List<String> cols = new ArrayList<>(Arrays.asList("etnia", "genero", "classe_social"));
            cols.retainAll(colunas);

            if(cols.isEmpty())
                return Optional.empty();

            Optional<HashMap<String, List<Estatistica>>> mapOpt = estatisticaRepository.obterEstatistica(cols);

            if(mapOpt.isPresent()){
                HashMap<String, List<Estatistica>> estatisticas = mapOpt.get();
                HashMap<String, List<EstatisticaDto>> hashEstatisticasDto = new HashMap<>();

                for(Map.Entry<String, List<Estatistica>> es: estatisticas.entrySet()) {
                    List<EstatisticaDto> estatisticasDto = new ArrayList<>();
                    for (Estatistica estatistica : es.getValue())
                        estatisticasDto.add(objectMapper.convertValue(estatistica, EstatisticaDto.class));

                    hashEstatisticasDto.put(es.getKey(), estatisticasDto);
                }

                return Optional.of(hashEstatisticasDto);
            }
            return Optional.empty();
        } catch (Exception e){
            throw new Exception();
        }
    }
}
