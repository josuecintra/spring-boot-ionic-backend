package com.nelioalves.cursomc.services;

import java.util.Date;

import com.nelioalves.cursomc.domain.Pedido;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

public abstract class AbstractEmailService implements EmailService {

    @Value("${default.sender}")
    private String sender;

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
}
