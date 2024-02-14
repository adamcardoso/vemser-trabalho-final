package br.com.dbc.vemser.notifica.service;

import br.com.dbc.vemser.notifica.dto.avisos.AvisosCreateDTO;
import br.com.dbc.vemser.notifica.dto.avisos.AvisosDTO;
import br.com.dbc.vemser.notifica.dto.localizacao.LocalizacaoCreateDTO;
import br.com.dbc.vemser.notifica.dto.localizacao.LocalizacaoDTO;
import br.com.dbc.vemser.notifica.entity.Avisos;
import br.com.dbc.vemser.notifica.entity.Instituicao;
import br.com.dbc.vemser.notifica.entity.Localizacao;
import br.com.dbc.vemser.notifica.entity.Usuario;
import br.com.dbc.vemser.notifica.entity.enums.UsuarioAtivo;
import br.com.dbc.vemser.notifica.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.notifica.repository.AvisosRepository;
import br.com.dbc.vemser.notifica.repository.InstituicaoRepository;
import br.com.dbc.vemser.notifica.repository.LocalizacaoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AvisosService {

    private final AvisosRepository avisosRepository;
    private final LocalizacaoRepository localizacaoRepository;
    private final InstituicaoRepository instituicaoRepository;
    private final EmailService emailService;
    private final LoginService loginService;

    public AvisosDTO saveAviso(AvisosCreateDTO avisoDTO) throws Exception {
        Localizacao localizacao = createLocalizacao(avisoDTO.getLocalizacao());

        Avisos aviso = convertAvisoCreateDTOToEntity(avisoDTO, localizacao);

        aviso.setLocalizacao(localizacao);
        Instituicao instituicao = loginService.getLoggedInstituicao();
        aviso.setInstituicao(instituicao);

        Avisos savedAviso = avisosRepository.save(aviso);

        enviarEmailsParaUsuariosNaMesmaLocalizacao(savedAviso);

        return convertAvisosToDTO(savedAviso);
    }
    private Localizacao createLocalizacao(LocalizacaoCreateDTO localizacaoDTO) {
        Localizacao localizacao = convertLocalizacaoDTOToEntity(localizacaoDTO);
        return localizacaoRepository.save(localizacao);
    }

    private Avisos convertAvisoCreateDTOToEntity(AvisosCreateDTO avisoDTO, Localizacao localizacao) throws RegraDeNegocioException {
        Avisos aviso = new Avisos();
        aviso.setMessage(avisoDTO.getMessage());
        aviso.setData(avisoDTO.getData());
        aviso.setHora(avisoDTO.getHora());
        aviso.setLocalizacao(localizacao);

        Instituicao instituicao = loginService.getLoggedInstituicao();
        aviso.setIdInstituicao(instituicao.getIdInstituicao());

        return aviso;
    }
    public AvisosDTO convertAvisosToDTO(Avisos aviso) {
        AvisosDTO avisoDTO = new AvisosDTO();
        avisoDTO.setMessage(aviso.getMessage());
        avisoDTO.setData(aviso.getData());
        avisoDTO.setHora(aviso.getHora());

        avisoDTO.setLocalizacao(convertLocalizacaoToDTO(aviso.getLocalizacao()));

        return avisoDTO;
    }
    private LocalizacaoDTO convertLocalizacaoToDTO(Localizacao localizacao) {
        LocalizacaoDTO localizacaoDTO = new LocalizacaoDTO();
        localizacaoDTO.setLatitude(localizacao.getLatitude());
        localizacaoDTO.setLongitude(localizacao.getLongitude());
        return localizacaoDTO;
    }
    private Localizacao convertLocalizacaoDTOToEntity(LocalizacaoCreateDTO localizacaoDTO) {
        Localizacao localizacao = new Localizacao();
        localizacao.setLatitude(localizacaoDTO.getLatitude());
        localizacao.setLongitude(localizacaoDTO.getLongitude());
        return localizacao;
    }


    public void enviarEmailsParaUsuariosNaMesmaLocalizacao(Avisos aviso) throws Exception {
        Localizacao localizacaoAviso = aviso.getLocalizacao();
        List<Usuario> usuariosNaMesmaLocalizacao = localizacaoRepository.findUsuariosByLocalizacao(
                localizacaoAviso.getLatitude(),
                localizacaoAviso.getLongitude()
        );

        for (Usuario usuario : usuariosNaMesmaLocalizacao) {
            if (usuario.getUsuarioAtivo() == UsuarioAtivo.SIM) {
                String emailUsuario = usuario.getEmailUsuario();
                enviarEmailParaUsuario(emailUsuario, aviso);
            }
        }
    }

    private void enviarEmailParaUsuario(String emailUsuario, Avisos aviso) throws Exception {
        String assunto = "Novo Aviso na Sua Localização";
        String corpo = "Olá,\n\n"
                + "Um novo aviso foi publicado na sua localização:\n\n"
                + "Mensagem: " + aviso.getMessage() + "\n"
                + "Data: " + aviso.getData() + "\n"
                + "Hora: " + aviso.getHora() + "\n\n"
                + "Atenciosamente,\nSua Plataforma de Avisos";

        emailService.enviarEmail(emailUsuario, assunto, corpo);
    }


}
