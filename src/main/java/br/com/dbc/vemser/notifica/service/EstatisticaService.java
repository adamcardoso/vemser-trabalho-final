package br.com.dbc.vemser.notifica.service;

import br.com.dbc.vemser.notifica.dto.estatistica.EstatisticaDTO;
import br.com.dbc.vemser.notifica.entity.enums.ClasseSocial;
import br.com.dbc.vemser.notifica.entity.enums.Etnia;
import br.com.dbc.vemser.notifica.entity.enums.Genero;
import br.com.dbc.vemser.notifica.repository.EstatisticaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class EstatisticaService {
    private final EstatisticaRepository estatisticaRepository;

    public EstatisticaDTO gerarEstatisticas() {
        EstatisticaDTO estatisticaDTO = new EstatisticaDTO();

        estatisticaDTO.setEtniaUsuario(calcularPercentagensEnum(estatisticaRepository.countByEtnia()));
        estatisticaDTO.setGeneroUsuario(calcularPercentagensEnum(estatisticaRepository.countByGenero()));
        estatisticaDTO.setClasseSocial(calcularPercentagensEnum(estatisticaRepository.countByClasseSocial()));

        estatisticaDTO.setUltimaDataCadastro(estatisticaRepository.ultimaCriacaoDenuncia());

        return estatisticaDTO;
    }

    public <T extends Enum<T>> Map<T, Double> calcularPercentagensEnum(List<Object[]> resultados) {
        Map<T, Long> contagens = resultados.stream()
                .collect(Collectors.toMap(result -> (T) result[0], result -> (Long) result[1]));

        long total = contagens.values().stream().mapToLong(Long::longValue).sum();

        return contagens.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, entry -> Double.parseDouble(formatDoubleWithTwoDecimals((entry.getValue().doubleValue() / total) * 100))));
    }

    private String formatDoubleWithTwoDecimals(double value) {
        return String.format("%.2f", value).replace(",", ".");
    }




}
