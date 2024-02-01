package br.com.dbc.vemser.notifica.service;

import br.com.dbc.vemser.notifica.dto.localizacao.LocalizacaoDto;
import br.com.dbc.vemser.notifica.entity.Denuncia;
import br.com.dbc.vemser.notifica.entity.Localizacao;
import br.com.dbc.vemser.notifica.repository.ILocalicacaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class LocalizacaoService {
    private final ILocalicacaoRepository localicacaoRepository;

    public List<LocalizacaoDto> listarLocalizacao(){
        return localicacaoRepository.findAll()
                .stream()
                .map(this::retornaLocalizacaoDto)
                .collect(Collectors.toList());
    }

    public void criarLocalizacao(Denuncia d){
        localicacaoRepository.save(new Localizacao(coordenadas(-90, 90), coordenadas(-180, 180), d));
    }

    private String coordenadas(int min, int max){
        DecimalFormat decimal = new DecimalFormat("#.####");
        return decimal.format(min + (max - min) * new Random().nextDouble());
    }

    private LocalizacaoDto retornaLocalizacaoDto(Localizacao l){
        return new LocalizacaoDto(l.getIdLocalizacao(), l.getLatitude(), l.getLongitude(), l.getDenuncia() != null ? l.getDenuncia().getIdDenuncia(): null);
    }
}
