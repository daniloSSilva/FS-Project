package br.com.fs.seeder;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import br.com.fs.model.Cliente;
import br.com.fs.model.Compra;
import br.com.fs.model.Contato;
import br.com.fs.model.Endereco;
import br.com.fs.model.Estado;
import br.com.fs.model.PessoaFisica;
import br.com.fs.model.PessoaJuridica;
import br.com.fs.model.Produto;
import br.com.fs.model.Usuario;
import br.com.fs.repository.ClienteRepository;
import br.com.fs.repository.CompraRepository;
import br.com.fs.repository.ProdutoRepository;

@Component
public class DbSeeder implements CommandLineRunner {

	private ClienteRepository clienteRepository;
	private CompraRepository compraRepository;
	private ProdutoRepository produtoRepository;

	public DbSeeder(ClienteRepository clienteRepository, CompraRepository compraRepository,
			ProdutoRepository produtoRepository) {
		this.clienteRepository = clienteRepository;
		this.compraRepository = compraRepository;
		this.produtoRepository = produtoRepository;
	}

	@Override
	public void run(String... args) throws Exception {
		List<Cliente> clientes = new ArrayList<>();

		this.clienteRepository.deleteAll();
		this.produtoRepository.deleteAll();
		this.compraRepository.deleteAll();
		
		{
			
			
			// Estrutura para Empresas
			Endereco endereco = new Endereco("Av. Paulista", 1001l, Estado.SAOPAULO, "São Paulo", "Brasil", null,
					"Boa Vista", new BigInteger("05796040"));

			List<Contato> contatos = new ArrayList<>();
			contatos.add(new Contato("empresa@empresa.com", "32224444"));
			contatos.add(new Contato("empresa_filial@empresa.com", "(21) 32224444"));

			PessoaJuridica pessoaJuridica = new PessoaJuridica("Empresa Ltda.", "Empresa", 12868788000131l);

			List<Usuario> usuarios = new ArrayList<>();
			usuarios.add(new Usuario("João da Silva"));
			usuarios.add(new Usuario("Maria José"));
			usuarios.add(new Usuario("Alberto Pereira"));
			usuarios.add(new Usuario("José Almeida"));
			usuarios.add(new Usuario("Gabriel Santos"));

			Cliente cliente = new Cliente("Empresa", pessoaJuridica, endereco, contatos, usuarios);

			// Adiciona FK´s
			pessoaJuridica.setPessoaJuridica(cliente);
			endereco.setCliente(cliente);
			// Add n contatos para 1 cliente
			for (Contato contato : contatos) {
				contato.setCliente(cliente);
			}
			// Add n contatos para 1 cliente
			for (Usuario usuario : usuarios) {
				usuario.setCliente(cliente);
			}

			clientes.add(cliente);

		}

		{
			Calendar cal = Calendar.getInstance();
			cal.set(1988, 12, 24);

			Endereco endereco = new Endereco("Rua Paulista", 91l, Estado.SAOPAULO, "Sao Paulo", "Brasil",
					"Casa 100", "Jd. Paulista", new BigInteger("19898333"));
			List<Contato> contatos = Arrays.asList(new Contato("jorge@gmail.com", "980362334"));
			PessoaFisica pessoaFisica = new PessoaFisica(37827265880l, 478790983l, cal.getTime());
			List<Usuario> usuarios = Arrays.asList(new Usuario("Jorge"));
			Cliente cliente = new Cliente("Jorge", pessoaFisica, endereco, contatos, usuarios);
			pessoaFisica.setPessoaFisica(cliente);
			endereco.setCliente(cliente);
			contatos.get(0).setCliente(cliente);
			usuarios.get(0).setCliente(cliente);
			clientes.add(cliente);

		}

		{
			Calendar cal = Calendar.getInstance();
			cal.set(1988, 12, 24);

			Endereco endereco = new Endereco("Rua das flores", 91l, Estado.SAOPAULO, "Sao Paulo", "Brasil", null,
					"Jd. das Flores", new BigInteger("04828373"));
			List<Contato> contatos = Arrays.asList(new Contato("alberto@gmail.com", "980362334"));
			PessoaFisica pessoaFisica = new PessoaFisica(37827265880l, 478790983l, cal.getTime());
			List<Usuario> usuarios = Arrays.asList(new Usuario("Alberto"));
			Cliente cliente = new Cliente("Alberto", pessoaFisica, endereco, contatos, usuarios);
			pessoaFisica.setPessoaFisica(cliente);
			endereco.setCliente(cliente);
			contatos.get(0).setCliente(cliente);
			usuarios.get(0).setCliente(cliente);
			clientes.add(cliente);

		}
		List<Produto> produtos = Arrays.asList(new Produto("Windows 10"), new Produto("Microsoft Office 2018"),
				new Produto("Avast Antivirus"));
		
		Compra compra = new Compra(clientes.get(0), produtos.get(0));
		clientes.get(0).setCompras(Arrays.asList(compra));

		this.produtoRepository.saveAll(produtos);
		this.clienteRepository.saveAll(clientes);
		this.compraRepository.save(compra);

	}

}
