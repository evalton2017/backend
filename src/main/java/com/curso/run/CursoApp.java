package com.curso.run;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.curso.run.model.Categoria;
import com.curso.run.model.Cidade;
import com.curso.run.model.Cliente;
import com.curso.run.model.Endereco;
import com.curso.run.model.Estado;
import com.curso.run.model.ItemPedido;
import com.curso.run.model.Pagamento;
import com.curso.run.model.PagamentoComBoleto;
import com.curso.run.model.PagamentoComCartao;
import com.curso.run.model.Pedido;
import com.curso.run.model.Produto;
import com.curso.run.model.enums.EstadoPagamento;
import com.curso.run.model.enums.TipoCliente;
import com.curso.run.repositories.CategoriaRepository;
import com.curso.run.repositories.CidadeRepository;
import com.curso.run.repositories.ClienteRepository;
import com.curso.run.repositories.EnderecoRepository;
import com.curso.run.repositories.EstadoRepository;
import com.curso.run.repositories.ItemPedidoRepository;
import com.curso.run.repositories.PagamentoRepository;
import com.curso.run.repositories.PedidoRepository;
import com.curso.run.repositories.ProdutoRepository;

@SpringBootApplication
public class CursoApp implements CommandLineRunner {
	
	@Autowired
	private CategoriaRepository catRepo;
	@Autowired
	private ProdutoRepository proRepo;
	@Autowired
	private EstadoRepository estRepo;
	@Autowired
	private CidadeRepository cidRepo;
	@Autowired
	private ClienteRepository cliRepo;
	@Autowired
	private EnderecoRepository endRepo;
	@Autowired
	private PedidoRepository pedRepo;
	@Autowired
	private PagamentoRepository pagRepo;
	@Autowired
	private ItemPedidoRepository itpRepo;

	public static void main(String[] args) {
		SpringApplication.run(CursoApp.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		//Instancia a categoria
		Categoria cat1 = new Categoria(null,"Informatica");
		Categoria cat2 = new Categoria(null,"Brinquedos");
		
		//Instancia os produtos
		Produto p1 = new Produto(null,"Notebook",2000D);
		Produto p2 = new Produto(null,"Impressora",850D);
		Produto p3 = new Produto(null,"Carrinho",35D);
		
		//associa as categorias aos produtos
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1));
		p3.getCategorias().addAll(Arrays.asList(cat2));
		
		//associa os produtos as categorias
		cat1.getProdutos().addAll(Arrays.asList(p1,p2));
		cat2.getProdutos().addAll(Arrays.asList(p3));
		
		//Salva categoria e produto				
		catRepo.saveAll(Arrays.asList(cat1,cat2));
		proRepo.saveAll(Arrays.asList(p1,p2,p3));
		
		Estado est1 = new Estado(null, "Goias");
		Estado est2 = new Estado(null, "Distrito Federal");
		
		Cidade cid1 = new Cidade(null,"Goiania", est1);
		Cidade cid2 = new Cidade(null,"Jardin Botanico", est2);
		Cidade cid3 = new Cidade(null,"São Sebastiao", est2);
		
		est1.getCidades().addAll(Arrays.asList(cid1));
		est2.getCidades().addAll(Arrays.asList(cid2,cid3));
		
		estRepo.saveAll(Arrays.asList(est1,est2));
		cidRepo.saveAll(Arrays.asList(cid1,cid2,cid3));
		
		//Instanciar Clientes e endereços
		Cliente cli1 = new Cliente(null,"Duke","duke@gmail.com","3256666699",TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("61-995662222","61-991383624"));
				
		Endereco e1 = new Endereco(null,"Quadra 14","29","","São jose","71693015",cli1,cid3);
		Endereco e2 = new Endereco(null,"Quadra 15","19","","São jose","71693023",cli1,cid3);
		
		cli1.getEnderecos().addAll(Arrays.asList(e1,e2));
		
		cliRepo.saveAll(Arrays.asList(cli1));
		endRepo.saveAll(Arrays.asList(e1,e2));
		
		//Instanciar pedido e pagamento
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyy HH:MM");
		Pedido ped1 = new Pedido(null,sdf.parse("30/02/2019 09:45"),cli1, e1);
		Pedido ped2 = new Pedido(null,sdf.parse("04/04/2019 14:35"),cli1, e2);
		
		Pagamento pg1 = new PagamentoComCartao(null,EstadoPagamento.QUITADO,ped1,3);
		ped1.setPagamento(pg1);
		
		Pagamento pg2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2,sdf.parse("08/04/2019 00:00"), null);
		ped2.setPagamento(pg2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1,ped2));
		
		pedRepo.saveAll(Arrays.asList(ped1,ped2));
		pagRepo.saveAll(Arrays.asList(pg1,pg2));
		
		//Instanciar itens 
		ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 2, 4000D);
		ItemPedido ip2 = new ItemPedido(ped2, p3, 0.00, 3, 105D);
		ItemPedido ip3 = new ItemPedido(ped2, p2, 0.00, 1, 850D);
		
		ped1.getItens().addAll(Arrays.asList(ip1));
		ped2.getItens().addAll(Arrays.asList(ip2,ip3));
		
		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip2,ip3));
		
		itpRepo.saveAll(Arrays.asList(ip1,ip2,ip3));
		
	}

}

