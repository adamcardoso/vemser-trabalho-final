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


<<<<<<< HEAD
            Optional<HashMap<String, List<Estatistica>>> mapOpt = estatisticaRepository.obterEstatistica(cols);

            if(mapOpt.isPresent()){
                HashMap<String, List<Estatistica>> estatisticas = mapOpt.get();
                HashMap<String, List<EstatisticaDTO>> hashEstatisticasDto = new HashMap<>();

                for(Map.Entry<String, List<Estatistica>> es: estatisticas.entrySet()) {
                    List<EstatisticaDTO> estatisticasDto = new ArrayList<>();
                    for (Estatistica estatistica : es.getValue())
                        estatisticasDto.add(objectMapper.convertValue(estatistica, EstatisticaDTO.class));

                    hashEstatisticasDto.put(es.getKey(), estatisticasDto);
                }

                return Optional.of(hashEstatisticasDto);
            }
            return Optional.empty();
        } catch (Exception e){
            e.printStackTrace();
            throw new Exception();
        }
    }
=======
>>>>>>> develop
}
