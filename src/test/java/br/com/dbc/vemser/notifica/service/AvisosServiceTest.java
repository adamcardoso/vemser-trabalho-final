package br.com.dbc.vemser.notifica.service;

import br.com.dbc.vemser.notifica.dto.avisos.AvisosCreateDTO;
import br.com.dbc.vemser.notifica.dto.avisos.AvisosDTO;
import br.com.dbc.vemser.notifica.dto.localizacao.LocalizacaoCreateDTO;
import br.com.dbc.vemser.notifica.entity.Avisos;
import br.com.dbc.vemser.notifica.entity.Instituicao;
import br.com.dbc.vemser.notifica.entity.Localizacao;
import br.com.dbc.vemser.notifica.entity.Usuario;
import br.com.dbc.vemser.notifica.entity.enums.UsuarioAtivo;
import br.com.dbc.vemser.notifica.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.notifica.repository.AvisosRepository;
import br.com.dbc.vemser.notifica.repository.LocalizacaoRepository;
import br.com.dbc.vemser.notifica.service.AvisosService;
import br.com.dbc.vemser.notifica.service.EmailService;
import br.com.dbc.vemser.notifica.service.LoginService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class AvisosServiceTest {

    @Mock
    private AvisosRepository avisosRepository;

    @Mock
    private LocalizacaoRepository localizacaoRepository;

    @Mock
    private LoginService loginService;

    @Mock
    private EmailService emailService;

    @InjectMocks
    private AvisosService avisosService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Deveria salvar aviso com sucesso")
    void deveriaSalvarAvisoComSucesso() throws RegraDeNegocioException { //68% pois nao testei email
        AvisosCreateDTO avisoCreateDTO = new AvisosCreateDTO();
        avisoCreateDTO.setMessage("Mensagem de teste");
        avisoCreateDTO.setData("Dia 12 de Abril");
        avisoCreateDTO.setHora("12:00");

        LocalizacaoCreateDTO localizacaoCreateDTO = new LocalizacaoCreateDTO();
        localizacaoCreateDTO.setLatitude("10.0");
        localizacaoCreateDTO.setLongitude("20.0");

        avisoCreateDTO.setLocalizacao(localizacaoCreateDTO);

        Localizacao localizacaoMock = new Localizacao();
        localizacaoMock.setLatitude("10.0");
        localizacaoMock.setLongitude("20.0");

        Avisos avisoMock = new Avisos();
        avisoMock.setMessage(avisoCreateDTO.getMessage());
        avisoMock.setData(avisoCreateDTO.getData());
        avisoMock.setHora(avisoCreateDTO.getHora());
        avisoMock.setLocalizacao(localizacaoMock);
        avisoMock.setIdInstituicao(1);

        Instituicao instituicaoMock = new Instituicao();
        instituicaoMock.setIdInstituicao(1);

        when(localizacaoRepository.save(any(Localizacao.class))).thenReturn(localizacaoMock);
        when(loginService.getLoggedInstituicao()).thenReturn(instituicaoMock);
        when(avisosRepository.save(any(Avisos.class))).thenReturn(avisoMock);

        AvisosDTO avisosDTO = avisosService.saveAviso(avisoCreateDTO);

        assertNotNull(avisosDTO, "O objeto AvisosDTO não deveria ser nulo");
        assertNotNull(localizacaoMock, "Localização não deve ser null");
        assertEquals(avisoCreateDTO.getMessage(), avisosDTO.getMessage(), "A mensagem não está correta");
        assertEquals(avisoMock.getData(), avisosDTO.getData(), "A data não está correta");
        assertEquals(avisoCreateDTO.getHora(), avisosDTO.getHora(), "A hora não está correta");
    }

}
