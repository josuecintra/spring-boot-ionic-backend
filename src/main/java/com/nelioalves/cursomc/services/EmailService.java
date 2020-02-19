package com.nelioalves.cursomc.services;

import org.springframework.mail.SimpleMailMessage;

import javax.mail.internet.MimeMessage;

import com.nelioalves.cursomc.domain.Pedido;

public interface EmailService {

	void sendOrderConfirmationEmail(Pedido o);

	void sendEmail(SimpleMailMessage msg);

	void sendOrderConfirmationHtmlEmail(Pedido o);

	void sendHtmlEmail(MimeMessage msg);


}


