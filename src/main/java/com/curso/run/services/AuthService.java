package com.curso.run.services;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.curso.run.model.Cliente;
import com.curso.run.repositories.ClienteRepository;
import com.curso.run.services.Exception.ObjectNotFoundException;
import com.curso.run.services.validation.EmailService;

@Service
public class AuthService {

	@Autowired
	private ClienteRepository clienteRepo;

	@Autowired
	private BCryptPasswordEncoder pe;

	@Autowired
	private EmailService emailService;

	private Random rand = new Random();

	public void SendNewPassword(String email) {
		Cliente cliente = clienteRepo.findByEmail(email);

		if (cliente == null) {
			throw new ObjectNotFoundException("email não encontrado");
		}

		String newPass = newPassword();
		cliente.setSenha(pe.encode(newPass));
		clienteRepo.save(cliente);
		emailService.sendNewPasswordEmail(cliente, newPass);
	}

	private String newPassword() {
		char[] vet = new char[10];
		for (int i = 0; i < 10; i++) {
			vet[i] = randonChar();
		}
		return new String(vet);
	}

	private char randonChar() {
		int opt = rand.nextInt(3);
		// gera um digito
		if (opt == 0) {
			return (char) (rand.nextInt(10)+48);
		}
		// gera letra maiuscula
		else if (opt == 1) {
			return (char)(rand.nextInt(26)+65);
		}
		//gera lera minuscula
		else {
			return (char)(rand.nextInt(26)+97);
		}
		
	}

}
