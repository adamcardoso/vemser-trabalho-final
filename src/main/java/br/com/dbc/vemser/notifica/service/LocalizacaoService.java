package br.com.dbc.vemser.notifica.service;

import br.com.dbc.vemser.notifica.dto.localizacao.LocalizacaoCreateDTO;
import br.com.dbc.vemser.notifica.entity.Localizacao;
import br.com.dbc.vemser.notifica.repository.ILocalicacaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class LocalizacaoService {
    private final ILocalicacaoRepository localicacaoRepository;
    private final RestTemplate restTemplate;

    @Value("${google.geocoding.api.key}")
    private String apiKey; // Adicione a sua chave de API do Google Geocoding no arquivo application.properties

    public List<LocalizacaoCreateDTO> listarLocalizacao() {
        return localicacaoRepository.findAll()
                .stream()
                .map(this::retornaLocalizacaoDto)
                .collect(Collectors.toList());
    }
    public String obterLinkGoogleMaps(String latitude, String longitude) {
        String baseGoogleMapsUrl = "https://www.google.com/maps/place/";
        String formattedCoordinates = String.format("%s,%s", latitude, longitude);
        return baseGoogleMapsUrl + formattedCoordinates;
    }

    private LocalizacaoCreateDTO retornaLocalizacaoDto(Localizacao l) {
        return new LocalizacaoCreateDTO(l.getLatitude(), l.getLongitude());
    }
}
