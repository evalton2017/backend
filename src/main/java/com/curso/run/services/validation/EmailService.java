package com.curso.run.services.validation;

import org.springframework.mail.SimpleMailMessage;

import com.curso.run.model.Pedido;

public interface EmailService {

	void sendOrderConfirmationEmail(Pedido obj);
	
	void sendEmail(SimpleMailMessage msg);
}
