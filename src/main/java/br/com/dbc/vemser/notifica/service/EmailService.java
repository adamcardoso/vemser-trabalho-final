package br.com.dbc.vemser.notifica.service;

import br.com.dbc.vemser.notifica.entity.Denuncia;
import br.com.dbc.vemser.notifica.entity.enums.TipoDenuncia;
import br.com.dbc.vemser.notifica.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.notifica.repository.DenunciaRepository;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final freemarker.template.Configuration configuracaoFreemarker;
    private final JavaMailSender remetenteEmail;
    private final DenunciaRepository denunciaRepository;

    private static final Logger logs = LoggerFactory.getLogger(EmailService.class);

    @Value("${spring.mail.username}")
    private String remetente;

    public void enviarEmail(String destinatario, String assunto, String aviso) throws RegraDeNegocioException {
        MimeMessage mensagemMime = remetenteEmail.createMimeMessage();
        try {
            MimeMessageHelper auxiliarMensagemMime = new MimeMessageHelper(mensagemMime, true);

            auxiliarMensagemMime.setFrom(remetente);
            auxiliarMensagemMime.setTo(destinatario);
            auxiliarMensagemMime.setSubject(assunto);
            auxiliarMensagemMime.setText(aviso);
            remetenteEmail.send(auxiliarMensagemMime.getMimeMessage());
        } catch (MessagingException e) {
            e.printStackTrace();
            throw new RegraDeNegocioException(e.getMessage());
        }
    }

}
