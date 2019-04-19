package com.curso.run.services.validation;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.SimpleMailMessage;

import com.curso.run.model.Cliente;
import com.curso.run.model.Pedido;

public interface EmailService {

	void sendOrderConfirmationEmail(Pedido obj);
	
	void sendEmail(SimpleMailMessage msg);
	
	void sendOrderConfirmationHtmlEmail(Pedido obj);
	
	void sendHtmlEmail(MimeMessage msg);
	
	void sendNewPasswordEmail(Cliente cliente, String newPass);
}
