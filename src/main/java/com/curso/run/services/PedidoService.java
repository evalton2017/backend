package com.curso.run.services;

import java.util.Arrays;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.curso.run.model.ItemPedido;
import com.curso.run.model.PagamentoComBoleto;
import com.curso.run.model.Pedido;
import com.curso.run.model.enums.EstadoPagamento;
import com.curso.run.repositories.ItemPedidoRepository;
import com.curso.run.repositories.PagamentoRepository;
import com.curso.run.repositories.PedidoRepository;
import com.curso.run.services.Exception.ObjectNotFoundException;


@Service
public class PedidoService {

	@Autowired
	private PedidoRepository repo;
	
	@Autowired
	private BoletoService bolService;
	
	@Autowired
	private PagamentoRepository pagRepo;
	
	@Autowired
	private ProdutoService prodService;
	
	@Autowired
	private ItemPedidoRepository itRepo;
	
	public Pedido buscar(Long id){
		Optional<Pedido> cat = repo.findById(id);
		return cat.orElseThrow(()-> new ObjectNotFoundException(
				"Pedido não encontrada Id:"+id+", tipo: "+ Pedido.class.getName()));
	}
	
	public Pedido insert(Pedido obj) {
		obj.setId(null);
		obj.setInstante(new Date());
		obj.getPagamento().setEstado(EstadoPagamento.PENDENTE);
		obj.getPagamento().setPedido(obj);
		if(obj.getPagamento() instanceof PagamentoComBoleto) {
			PagamentoComBoleto pagto = (PagamentoComBoleto) obj.getPagamento();
			bolService.preencherPagamentoComBoleto(pagto, obj.getInstante());
		}
		
		obj=repo.save(obj);
		pagRepo.save(obj.getPagamento());
		for(ItemPedido ip:obj.getItens()) {
			ip.setDesconto(0.0);
			ip.setPreco(prodService.buscar(ip.getProduto().getId()).getPreco());
			ip.setPedido(obj);
		}
		
		itRepo.saveAll(obj.getItens());
		
		return obj;
		
		
	}
	
	
	
}


