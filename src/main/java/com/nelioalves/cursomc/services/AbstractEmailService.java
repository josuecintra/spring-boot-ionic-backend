package com.nelioalves.cursomc.services;

import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import com.nelioalves.cursomc.domain.Cliente;
import com.nelioalves.cursomc.domain.Pedido;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

public abstract class AbstractEmailService implements EmailService {

    @Value("${default.sender}")
    private String sender;

    @Autowired
    private TemplateEngine templateEngine;

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void sendOrderConfirmationEmail(Pedido o) {
        SimpleMailMessage sm = prepareSimpleMailMessageFromPedido(o);
        sendEmail(sm);
    }

    protected SimpleMailMessage prepareSimpleMailMessageFromPedido(Pedido o) {
        SimpleMailMessage sm = new SimpleMailMessage();
        sm.setTo(o.getCliente().getEmail());
        sm.setFrom(sender);
        sm.setSubject("Pedido confirmado! Código: " + o.getId());
        sm.setSentDate(new Date(System.currentTimeMillis()));
        sm.setText(o.toString());

        return sm;
    }

    protected String htmlFromTemplatePedido(Pedido o) {
        Context context = new Context();
        context.setVariable("pedido", o);
        return templateEngine.process("email/confirmacaoPedido", context);
    }

    @Override
    public void sendOrderConfirmationHtmlEmail(Pedido o) {
        try {
            MimeMessage mm = prepareMimeMessageFromPedido(o);
            sendHtmlEmail(mm);            
        } catch (Exception e) {
            sendOrderConfirmationEmail(o);
        }
    }

    private MimeMessage prepareMimeMessageFromPedido(Pedido o) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mmh = new MimeMessageHelper(mimeMessage, true);
        mmh.setTo(o.getCliente().getEmail());
        mmh.setFrom(sender);
        mmh.setSubject("Pedido confirmado! Código: " + o.getId());
        mmh.setSentDate(new Date(System.currentTimeMillis()));
        mmh.setText(htmlFromTemplatePedido(o), true);
        return mimeMessage;
    }

    public void sendNewPasswordEmail(Cliente cliente, String newPass) {
        SimpleMailMessage mm = prepareNewPasswordEmail(cliente, newPass);
        sendEmail(mm);            
    }

    private SimpleMailMessage prepareNewPasswordEmail(Cliente cliente, String newPass) {
        SimpleMailMessage sm = new SimpleMailMessage();
        sm.setTo(cliente.getEmail());
        sm.setFrom(sender);
        sm.setSubject("Nova senha para recuperação de acesso");
        sm.setSentDate(new Date(System.currentTimeMillis()));
        sm.setText("Nova senha provisória: " + newPass);

        return sm;
    }
}
