package br.com.fs.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fs.dto.ProdutoDTO;
import br.com.fs.model.Produto;
import br.com.fs.repository.ProdutoRepository;

@Service
public class ProdutoService {
	@Autowired
	private ModelMapper modelMapper;
	private ProdutoRepository produtoRepository;

	public ProdutoService(ProdutoRepository produtoRepository) {
		this.produtoRepository = produtoRepository;
	}

	public List<Produto> getAll() {
		List<Produto> produtos = produtoRepository.findAll();
		return produtos;
	}

	public void save(Produto produto) {
		produtoRepository.save(produto);
	}

	public void delete(Long id) {
		produtoRepository.deleteById(id);
	}

	public Optional<Produto> getById(Long id) {
		return produtoRepository.findById(id);
	}

	public ProdutoDTO convertToDTO(Produto produto) {
		ProdutoDTO produtoDTO = modelMapper.map(produto, ProdutoDTO.class);
		return produtoDTO;
	}

	public Produto convertToEntity(ProdutoDTO produtoDTO) {
		Produto produto = modelMapper.map(produtoDTO, Produto.class);
		// populaRelacionamentos(produto);
		return produto;
	}

	// public void populaRelacionamentos(Produto produto) {
	// produto.set
	// }
}
