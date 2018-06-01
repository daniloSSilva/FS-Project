package br.com.fs.controller;

import java.io.Serializable;
import java.text.ParseException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.fs.dto.ProdutoDTO;
import br.com.fs.model.Produto;
import br.com.fs.service.ProdutoService;

/**
* Endpoint para métodos de produtos
*
* @author  Danilo Silva
* @since   2018-05-28 
*/

@RestController
@RequestMapping("/produto")

public class ProdutoController implements Serializable {

	@Autowired
	private ProdutoService produtoService;

	/**
	 * 
	 */
	private static final long serialVersionUID = -4566642518896523105L;

	@GetMapping("/todos")
	public List<ProdutoDTO> getAll() {
		List<Produto> produtos = produtoService.getAll();
		return produtos.stream().map(produto -> produtoService.convertToDTO(produto)).collect(Collectors.toList());
	}

	@GetMapping("/{id}")
	public ProdutoDTO getById(@PathVariable("id") Long id) {
		Optional<Produto> produtoDTO = produtoService.getById(id);
		return produtoService.convertToDTO(produtoDTO.get());
	}
	
	@PutMapping
	@ResponseBody
	public void update(@RequestBody ProdutoDTO produtoDTO) throws ParseException {
		Produto produto = produtoService.convertToEntity(produtoDTO);
		produtoService.save(produto);
	}

	@PostMapping
	public void insert(@RequestBody ProdutoDTO produtoDTO) throws ParseException {
		Produto produto = produtoService.convertToEntity(produtoDTO);
		produtoService.save(produto);
	}

	@DeleteMapping("{id}")
	public void delete(@PathVariable("id") Long id) {
		produtoService.delete(id);
	}

	

}
